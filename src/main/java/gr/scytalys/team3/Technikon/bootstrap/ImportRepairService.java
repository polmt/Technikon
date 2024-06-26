package gr.scytalys.team3.Technikon.bootstrap;

import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@AllArgsConstructor
@Configuration
@Slf4j
public class ImportRepairService {
private final RepairRepository repairRepository;
private final PropertyOwnerRepository propertyOwnerRepository;
private final PropertyRepository propertyRepository;

    private final static int numOfIterations = 20;

    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {
        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair("PAINTING");
        repairRepository.save(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair("INSULATION");
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair("FRAMES");
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair("PLUMBING");
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair("ELECTRICAL WORK");
        repairRepository.save(repair4);

        Faker faker = new Faker();
        for (int i=0; i<numOfIterations; i++){
            PropertyOwner po = createRandomPO();
            propertyOwnerRepository.save(po);

            double randomNum = Math.random();

            if (randomNum > 0.5){
                Property property = new Property();
                property.setPropertyIN(faker.number().digits(9));
                property.setPropertyOwner(po);
                propertyRepository.save(property);
            }
        }
    }

    public PropertyOwner createRandomPO(){
        Faker faker = new Faker();

        PropertyOwner po = new PropertyOwner();
        po.setTin(faker.number().digits(9));
        po.setName(faker.name().firstName());
        po.setSurname(faker.name().lastName());
        po.setAddress(faker.address().fullAddress());
        po.setPhoneNumber("69" + faker.numerify("##########"));
        po.setEmail(faker.internet().emailAddress());
        po.setUsername(faker.name().username());
        po.setPassword(faker.internet().password());
        po.setActive(true);
        return po;
    }
}
