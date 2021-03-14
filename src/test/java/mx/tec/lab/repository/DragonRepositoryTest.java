package mx.tec.lab.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.tec.lab.entity.Dragon;
import mx.tec.lab.repository.DragonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;

	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
	}
	
	@Test
	public void givenDragon_whenExisting_thenModify() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		retrievedDragon.setName("Meraxesss");
		dragonRepository.save(retrievedDragon);
		
		
		Dragon retrievedDragon2 = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxesss", retrievedDragon2.getName());
	}
	
	@Test
	public void givenDragon_whenExisting_thenRemove() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon dragonToRemove = dragonRepository.findById(1L).orElse(null);
		dragonRepository.delete(dragonToRemove);
		
		Dragon dragonRemoved = dragonRepository.findById(1L).orElse(null);
		assertEquals(null, dragonRemoved);

	}
}