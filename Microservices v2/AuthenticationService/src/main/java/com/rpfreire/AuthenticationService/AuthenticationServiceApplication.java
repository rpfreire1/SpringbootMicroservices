package com.rpfreire.AuthenticationService;

import com.rpfreire.AuthenticationService.entity.PermissionEntity;
import com.rpfreire.AuthenticationService.entity.RoleEntity;
import com.rpfreire.AuthenticationService.entity.RoleEnum;
import com.rpfreire.AuthenticationService.entity.UserEntity;
import com.rpfreire.AuthenticationService.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.util.Set;

@SpringBootApplication
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserEntityRepository userEntityRepository) {
		return args -> {
			/* Create Permisions */
			PermissionEntity createPermission = PermissionEntity
					.builder()
					.name("CREATE")
					.description("Create permission")
					.build();
			PermissionEntity readPermission = PermissionEntity
					.builder()
					.name("READ")
					.description("Read permission")
					.build();
			PermissionEntity updatePermission = PermissionEntity
					.builder()
					.name("UPDATE")
					.description("Update permission")
					.build();
			PermissionEntity deletePermission = PermissionEntity
					.builder()
					.name("DELETE")
					.description("Delete permission")
					.build();
			/* Create ROLES */
			RoleEntity roleAdmin = RoleEntity
					.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(
							Set.of(
									createPermission,
									readPermission,
									updatePermission,
									deletePermission
							)
					)
					.build();
			RoleEntity roleUser = RoleEntity
					.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(
							Set.of(
									createPermission,
									readPermission,
									updatePermission
							)
					)
					.build();
			RoleEntity roleGuest = RoleEntity
					.builder()
					.roleEnum(RoleEnum.GUEST)
					.permissions(
							Set.of(
									readPermission
							)
					)
					.build();
			RoleEntity roleDeveloper = RoleEntity
					.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(
							Set.of(
									createPermission,
									readPermission,
									updatePermission,
									deletePermission
							)
					)
					.build();
		/* Create Users*/
		UserEntity userAdmin = UserEntity
				.builder()
				.username("admin")
				.password("$2a$10$5VyB0MoJjrAAh8Rul4Ypru2lCiFM3CKh4iuLz4nv6uEJ0.RQskT1a")
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.roles(Set.of(roleAdmin))
				.build();
		UserEntity userUser = UserEntity
				.builder()
				.username("user")
				.password("$2a$10$5VyB0MoJjrAAh8Rul4Ypru2lCiFM3CKh4iuLz4nv6uEJ0.RQskT1a")
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.roles(Set.of(roleUser))
				.build();
		UserEntity userGuest = UserEntity
				.builder()
				.username("guest")
				.password("$2a$10$5VyB0MoJjrAAh8Rul4Ypru2lCiFM3CKh4iuLz4nv6uEJ0.RQskT1a")
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.roles(Set.of(roleGuest))
				.build();
		UserEntity userDeveloper = UserEntity
				.builder()
				.username("developer")
				.password("$2a$10$5VyB0MoJjrAAh8Rul4Ypru2lCiFM3CKh4iuLz4nv6uEJ0.RQskT1a")
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.roles(Set.of(roleDeveloper))
				.build();

			userEntityRepository.saveAll(Set.of(userAdmin,userUser,userGuest,userDeveloper));
		};
	}
}
