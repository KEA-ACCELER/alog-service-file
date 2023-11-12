 # 🌼 동시편집 기능을 제공하는 릴리즈 노트 공유 시스템, A-LOG

**개발 기간** 2023.06 ~ 2023.08 <br/>
**사이트 바로가기** https://alog.acceler.kr/ (🔧업데이트 중) <br/>
**Team repo** https://github.com/orgs/KEA-ACCELER/repositories <br/>

# 🐳 Overview Architecture

![image](https://github.com/KEA-ACCELER/alog-service-project/assets/80394866/b9f31a1a-6375-4f6e-af24-02d4b308002a)

Here, the domains(service)'s relationship is shown. <br/>
![image](https://github.com/KEA-ACCELER/alog-service-project/assets/80394866/13671479-576b-4f50-a552-a7727b45cae0)

# 📚  Implementation

The purpose of the File service is for users to store and manage files in a spring-based, secure and scalable manner. This service provides file storage functionality by utilizing Rook and Ceph technology, and supports uploading, downloading, and deleting files.

## Service Flow and Features

### **1. File Upload**

- Users upload files through the API.
- The uploaded file is safely stored in the storage system.

### **2. File Download**

- Users download stored files through the API.
- Search for files in the repository and download them.

### **3. File Delete**

- Users delete files that are no longer needed.
- Delete the file from the repository and free up space.

## ERD
![image](https://github.com/KEA-ACCELER/alog-service-project/assets/80394866/46b908b4-1e76-4039-9895-5dbd5ea7c3f8)



# ✨ Installation

## Running the user app only 

- use docker-compose.yml
```
docker compose up -d
```

# 📝 Conclusion and Suggestion

## **If try to improve the quality**

- Performance optimization: Optimize Spring Boot application and Ceph configuration to improve file processing performance.
- Improve user experience: Improve API design and documentation to make it easy for users to use file upload, download, and management functions.

## **If try to improve the features**

- File version management: Add a function to store and manage previous versions of files to track and manage modification history.
- File sharing function: Share files with other users and enable access control through permission settings.

## **Conclusion**

The File service is a secure and scalable file storage and management solution that provides users with a convenient file management experience. It is important to continuously improve quality and add features to increase user satisfaction. It plays an important role in building a secure and scalable file storage and management solution by utilizing Spring Boot and related libraries. Users can upload and download files through the API, and manage files stably by utilizing the Ceph storage system.
