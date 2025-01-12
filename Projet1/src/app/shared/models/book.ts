export class Book {
    id?: string;
    title?: string;

    constructor(args: Book = {}){
        this.id=args.id;
        this.title=args.title;
    }

}
