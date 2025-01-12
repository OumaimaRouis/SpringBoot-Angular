import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { BookService } from '../service/book.service';
import { CommonModule } from '@angular/common';
import { AuthorService } from '../service/author.service';
import { BookDetailsService } from '../service/bookDetails.service';

interface BootstrapModal {
  new (element: HTMLElement): {
    show: () => void;
    hide: () => void;
  };
}
declare const bootstrap: { Modal: BootstrapModal };

@Component({
  selector: 'app-book-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-dashboard.component.html',
  styleUrls: ['./book-dashboard.component.css']
})
export class BookDashboardComponent implements OnInit {
  formValue!: FormGroup;
  books: any[] = [];
  authors: any[] = [];
  newBook: any = {};
  bookDetails: any[] = [];
  selectedImage: any = null;


  constructor(private fb: FormBuilder, private bookService: BookService, private bookDetailsService : BookDetailsService) {}

  ngOnInit(): void {
    this.initializeForm();
    this.fetchBooks();
    this.fetchAuthors();
    this.fetchBookDetails();

  }

  // Initialize the form
  initializeForm(): void {
    this.formValue = this.fb.group({
      id: ['', Validators.required],
      title: ['', Validators.required],
      author: ['', Validators.required],
      bookDetails: ['', Validators.required],
      img: ['', Validators.required]
    });
  }

  // Fetch all books
  fetchBooks(): void {
    this.bookService.getBooks().subscribe(
      (data) => {
        this.books = data;
        this.books.forEach(book => {
          if (book.img) {
            this.loadImage(book.id); // Call loadImage for each book with an image
          }
        });
      },
      (err) => {
        console.error('Error fetching books:', err);
      }
    );
  }
  
  loadImage(bookId: number): void {
    this.bookService.getImage(bookId).subscribe(
      (imageBlob) => {
        const imageObjectUrl = URL.createObjectURL(imageBlob);
        const book = this.books.find((book) => book.id === bookId);
        if (book) {
          book.img = imageObjectUrl;  // Store the image URL in the book object
        }
      },
      (err) => {
        console.error('Error fetching image:', err);  // Log the error message
      }
    );
  }
  
  
  

  // Fetch all authors
  fetchAuthors(): void {
    this.bookService.getAuthors().subscribe(
      (authors) => {
        this.authors = authors;
      },
      (err) => {
        console.error('Error fetching authors:', err);
      }
    );
  }
  fetchBookDetails(): void {
    this.bookDetailsService.getAuthors().subscribe(
      (details) => {
        this.bookDetails = details;
      },
      (err) => {
        console.error('Error fetching book details:', err);
      }
    );
  }
 

  onFileChange(event: any): void {
    // Ensure you're getting the file from the file input
    const file = event.target.files[0];
  
    // If a file is selected, assign it to the variable
    if (file) {
      this.selectedImage = file;
    }
  }


  // Add a new book
  addBook(): void {
    const newBook = this.formValue.value;
    //newBook.author = { id: Number(newBook.author) };
    //newBook.bookDetails = { id: Number(newBook.bookDetails) };
  
    const formData = new FormData();
    formData.append('produit', JSON.stringify({
      title: newBook.title,
      author: { id: Number(newBook.author) },
      bookDetails: { id: Number(newBook.bookDetails) },
    }));
    if (this.selectedImage) {
      formData.append('mf', this.selectedImage, this.selectedImage.name); // Add the file itself
    }

    formData.forEach((value, key) => {
      console.log(key + ": " + value);
    });

    console.log('Payload:', formData);
    this.bookService.addBook(formData).subscribe(
      () => {
        alert('Book added successfully');
        this.fetchBooks();
        this.formValue.reset();
        this.selectedImage = null;
      },
      (err) => {
        alert('Something went wrong while adding the book');
        console.error(err);
      }
    );
  }

  // Edit a book
  onEdit(row: any): void {
    this.formValue.patchValue({
      id: row.id,
      title: row.title,
      author: row.author?.id || '',
      bookDetails: row.bookDetails?.id || '',
      img: row.img
    });

    this.newBook = { ...row };
  }

  // Update an existing book
  updateBook(): void {
    const updatedBook = this.formValue.value;

    this.bookService.updateBook(updatedBook, updatedBook.id).subscribe(
      () => {
        alert('Book updated successfully');
        this.fetchBooks();
        this.formValue.reset();
      },
      (err) => {
        alert('Something went wrong while updating the book');
        console.error(err);
      }
    );
  }

  // Delete a book
  deleteBook(row: any): void {
    this.bookService.deleteBook(row.id).subscribe(
      () => {
        alert('Book deleted successfully');
        this.fetchBooks();
      },
      (err) => {
        alert('Something went wrong while deleting the book');
        console.error(err);
      }
    );
  }
}
