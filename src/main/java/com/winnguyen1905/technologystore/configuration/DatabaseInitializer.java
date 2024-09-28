package com.winnguyen1905.technologystore.configuration;

import java.util.Set;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.CityEntity;
import com.winnguyen1905.technologystore.entity.DistrictEntity;
import com.winnguyen1905.technologystore.entity.PermissionEntity;
import com.winnguyen1905.technologystore.entity.RoleEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.repository.DistrictRepository;
import com.winnguyen1905.technologystore.repository.PermissionRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;

@Service
public class DatabaseInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final DistrictRepository districtRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(PermissionRepository permissionRepository, DistrictRepository districtRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.permissionRepository = permissionRepository;
        this.districtRepository = districtRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (permissionRepository.findAll().size() != 0) return;

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
        permission.setName("Admin full permission");
        permission.setMethod("GET");

        // final PermissionEntity permission2 = new PermissionEntity();
        // permission.setApiPath("/api/v1/buildings");
        // permission.setCode("building");
        // permission.setName("create building");
        // permission.setMethod("POST");

        final RoleEntity role = new RoleEntity();
        role.setCode("admin");
        role.setName("ADMIN");
        role.setPermissions(Set.of(permission));
        permission.setRoles(Set.of(role));
        this.permissionRepository.save(permission);

        // User
        UserEntity user = new UserEntity();
        user.setUsername("baokhung2k4");
        user.setPassword(this.passwordEncoder.encode("12345678"));
        user.setRole(role);
        user.setType("customer");
        user.setId(UUID.fromString("16d3ffd5-1537-4b53-a031-ce6f71fcd06c"));
        this.userRepository.save(user);
    }
    
}