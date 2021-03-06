package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*  book - from OneToMany relation with author
 *   books - fro ManyToMany relationship with coauthors*/
@Getter
@Setter
@Entity
@Table(name = "author_table", schema = "library")
public class Author {
    @Id
    @Column(name = "author_ID")
    @GeneratedValue(generator = "increment")
    private int author_ID;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author() {
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> booksOfAuthor = new ArrayList<>();


    @ManyToMany(mappedBy = "coauthors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();


    @Override
    public String toString() {
        return "Author{" +
                "author_ID=" + author_ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", book=" + booksOfAuthor +
                ", books=" + books +
                '}';
    }
}
