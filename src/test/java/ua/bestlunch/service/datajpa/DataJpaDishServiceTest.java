package ua.bestlunch.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ua.bestlunch.service.AbstractDishServiceTest;

import static ua.bestlunch.Profiles.DATAJPA;

/**
 * Created by Виктор on 21.10.2016.
 */

@ActiveProfiles(DATAJPA)
public class DataJpaDishServiceTest extends AbstractDishServiceTest{
}
