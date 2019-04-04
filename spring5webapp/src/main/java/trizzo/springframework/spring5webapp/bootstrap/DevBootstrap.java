package trizzo.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import trizzo.springframework.spring5webapp.model.Author;
import trizzo.springframework.spring5webapp.model.Book;
import trizzo.springframework.spring5webapp.repositories.AuthorRepository;
import trizzo.springframework.spring5webapp.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{

	private AuthorRepository authorRepository;
	private BookRepository bookRepository; 
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) 
	{
		initData();
	}
	
	private void initData () 
	{
		// Eric
		Author eric = new Author("Eric", "Evans"); 
		Book bookOne = new Book("Horse Boxing: the Reckoning", "0001", "Penguin Publishing");
		eric.getBooks().add(bookOne);
		bookOne.getAuthors().add(eric); 
		
		authorRepository.save(eric);
		bookRepository.save(bookOne);
		
		// Jane
		Author jane = new Author("Jane", "Sweeney");
		Book bookTwo = new Book("Taekwondo for Cats", "5833", "Golden Books");
		
		authorRepository.save(jane);
		bookRepository.save(bookTwo);
	}
	
}
