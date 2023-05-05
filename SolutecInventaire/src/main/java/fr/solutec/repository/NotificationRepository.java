package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
