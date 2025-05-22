# 📱 휴대폰 판매 시스템 v1
> **[👉 GitHub Repository 바로가기](https://github.com/jmk445/ureca-phone-manager.git)**  

# 📱 휴대폰 판매 시스템 v2

> **Spring Boot 기반**의 휴대폰 판매 관리 시스템  
> Spring Data JPA, Lombok 적용으로 더 깔끔하고 효율적인 코드 구현

---

## 🛠 주요 기술 스택

| 기술 | 설명 |
|------|------|
| **Spring Boot** | 자바 기반의 백엔드 프레임워크 |
| **Spring Data JPA** | ORM 기반의 데이터 액세스 계층 자동화 |
| **Lombok** | 반복되는 코드 제거 (예: Getter/Setter, Constructor) |
| **H2 Database** | 인메모리 테스트용 DB |
| **Thymeleaf** | 서버 사이드 템플릿 엔진 |
| **Gradle** | 빌드 및 의존성 관리 |

---

## ✅ 개선 사항 (v1 기준)

### 🔸 Spring Data JPA 적용
- 기존의 DAO 및 SQL 중심 코드 제거
- `@Entity`, `@Repository`, `@Transactional` 구조로 간결한 JPA 아키텍처 구성
- CRUD 메서드 자동 생성 (`findById`, `save`, `deleteById` 등)

### 🔸 Lombok 적용
- 모델 클래스에 `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` 등 적용
- 불필요한 보일러플레이트 코드 제거
- 유지보수성과 가독성 향상

---

