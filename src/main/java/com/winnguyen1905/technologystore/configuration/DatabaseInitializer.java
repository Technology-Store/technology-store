package com.winnguyen1905.technologystore.configuration;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.CityEntity;
import com.winnguyen1905.technologystore.entity.DistrictEntity;
import com.winnguyen1905.technologystore.entity.PermissionEntity;
import com.winnguyen1905.technologystore.entity.RoleEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.repository.DistrictRepository;
import com.winnguyen1905.technologystore.repository.PermissionRepository;
import com.winnguyen1905.technologystore.repository.RoleRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;

@Service
public class DatabaseInitializer implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final DistrictRepository districtRepository;
    private final UserRepository userRepository;

    public DatabaseInitializer(PermissionRepository permissionRepository, DistrictRepository districtRepository,
            UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.districtRepository = districtRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(permissionRepository.findAll().size() != 0) return;

        // location
        final DistrictEntity district = new DistrictEntity();
        district.setCode("thu-duc");
        district.setName("Thu Duc");
        district.setCreatedBy("ADMINSTRATOR");
        final CityEntity city = new CityEntity();
        city.setName("Ho Chi Minh");
        city.setCode("ho-chi-minh");
        district.setCity(city);
        districtRepository.save(district);

        // permission
        final PermissionEntity permission = new PermissionEntity();
        permission.setApiPath("/api/v1/");
        permission.setCode("admin");
        permission.setName("Admin full role");
        permission.setMethod("GET");

        // final PermissionEntity permission2 = new PermissionEntity();
        // permission.setApiPath("/api/v1/buildings");
        // permission.setCode("building");
        // permission.setName("create building");
        // permission.setMethod("POST");

        final RoleEntity role = new RoleEntity();
        role.setCode("admin");
        role.setName("ADMIN");
        role.setPermissions(List.of(permission));
        permission.setRoles(List.of(role));
        this.permissionRepository.save(permission);

        // user
        UserEntity user = new UserEntity();
        user.setUsername("baokhung2k4");
        user.setPassword("12345678");
        user.setRole(role);
        user.setType("customer");
        this.userRepository.save(user);
    }
}