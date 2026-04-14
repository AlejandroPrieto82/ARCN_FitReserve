@echo off
echo ==========================================
echo   Creating FitReserve FULL structure
echo ==========================================

REM ================= DOMAIN =================

REM Models
type nul > src\main\java\com\fitreserve\domain\model\User.java
type nul > src\main\java\com\fitreserve\domain\model\GymClass.java
type nul > src\main\java\com\fitreserve\domain\model\Reservation.java
type nul > src\main\java\com\fitreserve\domain\model\ClassType.java

REM Value Objects
type nul > src\main\java\com\fitreserve\domain\valueobject\Email.java
type nul > src\main\java\com\fitreserve\domain\valueobject\Cupo.java
type nul > src\main\java\com\fitreserve\domain\valueobject\TimeSlot.java
type nul > src\main\java\com\fitreserve\domain\valueobject\UserId.java
type nul > src\main\java\com\fitreserve\domain\valueobject\ClassId.java
type nul > src\main\java\com\fitreserve\domain\valueobject\ReservationId.java

REM Enums
type nul > src\main\java\com\fitreserve\domain\model\UserRole.java
type nul > src\main\java\com\fitreserve\domain\model\ReservationStatus.java

REM Repositories
type nul > src\main\java\com\fitreserve\domain\repository\UserRepository.java
type nul > src\main\java\com\fitreserve\domain\repository\GymClassRepository.java
type nul > src\main\java\com\fitreserve\domain\repository\ReservationRepository.java
type nul > src\main\java\com\fitreserve\domain\repository\ClassTypeRepository.java

REM Events
type nul > src\main\java\com\fitreserve\domain\event\ReservationCreatedEvent.java
type nul > src\main\java\com\fitreserve\domain\event\ReservationCancelledEvent.java
type nul > src\main\java\com\fitreserve\domain\event\ClassCreatedEvent.java
type nul > src\main\java\com\fitreserve\domain\event\UserDeactivatedEvent.java
type nul > src\main\java\com\fitreserve\domain\event\ClassFullEvent.java

REM Exceptions
type nul > src\main\java\com\fitreserve\domain\exception\BusinessException.java
type nul > src\main\java\com\fitreserve\domain\exception\NotFoundException.java
type nul > src\main\java\com\fitreserve\domain\exception\ValidationException.java


REM ================= APPLICATION =================

REM Use Cases
type nul > src\main\java\com\fitreserve\application\usecase\CreateReservationUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\CancelReservationUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\GetUserReservationsUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\CreateGymClassUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\UpdateGymClassUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\DeleteGymClassUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\CreateUserUseCase.java
type nul > src\main\java\com\fitreserve\application\usecase\DeactivateUserUseCase.java

REM Services
type nul > src\main\java\com\fitreserve\application\service\ReservationService.java
type nul > src\main\java\com\fitreserve\application\service\GymClassService.java
type nul > src\main\java\com\fitreserve\application\service\UserService.java

REM DTOs
type nul > src\main\java\com\fitreserve\application\dto\CreateReservationRequest.java
type nul > src\main\java\com\fitreserve\application\dto\CreateGymClassRequest.java
type nul > src\main\java\com\fitreserve\application\dto\CreateUserRequest.java
type nul > src\main\java\com\fitreserve\application\dto\ReservationResponse.java
type nul > src\main\java\com\fitreserve\application\dto\GymClassResponse.java
type nul > src\main\java\com\fitreserve\application\dto\UserResponse.java


REM ================= INFRASTRUCTURE =================

REM Entities
type nul > src\main\java\com\fitreserve\infrastructure\persistence\entity\UserEntity.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\entity\GymClassEntity.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\entity\ReservationEntity.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\entity\ClassTypeEntity.java

REM JPA Repositories
type nul > src\main\java\com\fitreserve\infrastructure\persistence\repository\UserJpaRepository.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\repository\GymClassJpaRepository.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\repository\ReservationJpaRepository.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\repository\ClassTypeJpaRepository.java

REM Mappers
type nul > src\main\java\com\fitreserve\infrastructure\persistence\mapper\UserMapper.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\mapper\GymClassMapper.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\mapper\ReservationMapper.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\mapper\ClassTypeMapper.java
type nul > src\main\java\com\fitreserve\infrastructure\persistence\mapper\DomainMapper.java

REM Config
type nul > src\main\java\com\fitreserve\infrastructure\config\DatabaseConfig.java
type nul > src\main\java\com\fitreserve\infrastructure\config\SwaggerConfig.java
type nul > src\main\java\com\fitreserve\infrastructure\config\MapperConfig.java

REM External
type nul > src\main\java\com\fitreserve\infrastructure\external\EmailService.java
type nul > src\main\java\com\fitreserve\infrastructure\external\NotificationService.java


REM ================= INTERFACES =================

REM Controllers
type nul > src\main\java\com\fitreserve\interfaces\rest\controller\UserController.java
type nul > src\main\java\com\fitreserve\interfaces\rest\controller\GymClassController.java
type nul > src\main\java\com\fitreserve\interfaces\rest\controller\ReservationController.java
type nul > src\main\java\com\fitreserve\interfaces\rest\controller\AuthController.java

REM Requests
type nul > src\main\java\com\fitreserve\interfaces\rest\request\CreateReservationRequest.java
type nul > src\main\java\com\fitreserve\interfaces\rest\request\CreateGymClassRequest.java
type nul > src\main\java\com\fitreserve\interfaces\rest\request\CreateUserRequest.java

REM Responses
type nul > src\main\java\com\fitreserve\interfaces\rest\response\ReservationResponse.java
type nul > src\main\java\com\fitreserve\interfaces\rest\response\GymClassResponse.java
type nul > src\main\java\com\fitreserve\interfaces\rest\response\UserResponse.java


REM ================= SHARED =================

type nul > src\main\java\com\fitreserve\shared\constants\Roles.java
type nul > src\main\java\com\fitreserve\shared\util\DateUtils.java
type nul > src\main\java\com\fitreserve\shared\util\UuidGenerator.java
type nul > src\main\java\com\fitreserve\shared\util\ApiResponse.java


echo ==========================================
echo   STRUCTURE CREATED SUCCESSFULLY
echo ==========================================
pause