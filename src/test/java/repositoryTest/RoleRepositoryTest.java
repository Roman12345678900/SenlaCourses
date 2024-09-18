package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Role;
import senla.com.repository.implementation.RoleRepositoryImpl;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ApplicationConfiguration.class,
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RoleRepositoryTest {

    @Resource
    private RoleRepositoryImpl roleRepository;

    @Test
    public void TestFindById() {
        Role role = Role.builder()
                .id(1L)
                .name("ADMIN")
                .build();
        roleRepository.save(role);

        Role foundRole = roleRepository.findById(role.getId());

        assertNotNull(foundRole);
        assertEquals(role.getId(), foundRole.getId());
        assertEquals(role.getName(), foundRole.getName());
    }

    @Test
    public void TestFindAll() {
        Role role1 = Role.builder()
                .id(2L)
                .name("USER")
                .build();

        Role role2 = Role.builder()
                .id(3L)
                .name("MODERATOR")
                .build();

        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Role> roles = roleRepository.findAll();

        assertEquals(2, roles.size());
    }

    @Test
    public void TestSave() {
        Role role = Role.builder()
                .id(4L)
                .name("GUEST")
                .build();
        roleRepository.save(role);

        Role savedRole = roleRepository.findById(role.getId());

        assertNotNull(savedRole);
        assertEquals(role.getName(), savedRole.getName());
    }

    @Test
    public void TestDelete() {
        Role role = Role.builder()
                .id(5L)
                .name("SUPERADMIN")
                .build();
        roleRepository.save(role);

        roleRepository.deleteById(role.getId());

        Role deletedRole = roleRepository.findById(role.getId());

        assertNull(deletedRole);
    }
}
