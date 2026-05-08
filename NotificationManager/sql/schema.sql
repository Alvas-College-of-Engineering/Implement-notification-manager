CREATE DATABASE IF NOT EXISTS notification_manager;
USE notification_manager;
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type ENUM('INFO','WARNING','ERROR','SUCCESS') DEFAULT 'INFO',
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    source VARCHAR(100) DEFAULT 'System',
    priority ENUM('LOW','MEDIUM','HIGH','CRITICAL') DEFAULT 'MEDIUM'
);
INSERT INTO notifications (title,message,type,is_read,source,priority) VALUES
('Welcome!','Welcome to Notification Manager. System is ready.','SUCCESS',FALSE,'System','LOW'),
('Update Available','Version 2.1.0 is now available.','INFO',FALSE,'Updater','MEDIUM'),
('Disk Space Warning','Disk usage at 85%. Consider freeing space.','WARNING',FALSE,'Monitor','HIGH'),
('Backup Completed','Daily backup completed successfully at 02:00 AM.','SUCCESS',TRUE,'Backup','MEDIUM'),
('Login Attempt','Login attempt from IP 192.168.1.105.','WARNING',FALSE,'Security','HIGH'),
('Server Error','Internal error on node-3. Auto-recovery started.','ERROR',FALSE,'Server','CRITICAL'),
('Report Ready','Monthly analytics report is ready.','INFO',TRUE,'Analytics','LOW'),
('CPU Spike','CPU usage exceeded 90% for 5+ minutes.','ERROR',FALSE,'Monitor','CRITICAL');
