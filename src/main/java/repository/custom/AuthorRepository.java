package repository.custom;

import model.dto.Author;
import model.dto.Book;
import repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,String> {

}
