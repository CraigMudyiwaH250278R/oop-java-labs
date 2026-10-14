package lab09;

import java.util.Objects;

/**
 * Exercise 9.3 - equals/hashCode based on ISBN ONLY (the identity of a book).
 * CONTRACT: equal objects MUST have equal hash codes, otherwise a HashSet puts "equal" books in
 * different buckets and fails to detect duplicates. Hence both are overridden together.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn()  { return isbn; }
    public String getTitle() { return title; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() { return title + " [" + isbn + "]"; }
}
