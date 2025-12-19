# FITNESS APPLICATION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1)User & Activity Services: Designed and built secure, RESTful APIs for User Management (MySQL/JPA) and Activity Tracking (MongoDB), supporting full CRUD operations for user profiles and fitness records. 
Achieved horizontal scaling readiness by implementing service registration and discovery using Eureka Server.

2)Interservice Communication: Implemented synchronous interservice communication using Spring Cloud for data fetching (e.g., retrieving user details when processing an activity). 
Established robust, asynchronous communication between the Activity Producer and AI Consumer services using Apache Kafka, ensuring non-blocking processing of new activity data.

3)AI-Driven Recommendation Engine: Integrated Generative AI capabilities using Spring AI and Google Gemini models within a dedicated AI Service. 
Processed activity data asynchronously via Kafka to generate personalized fitness recommendations and safety guidelines, creating value-added data.

4)Security & Authentication: Secured the entire microservices ecosystem using OAuth2 (PKCE Flow) managed by Keycloak Identity Provider. 
Configured Spring Cloud Gateway to act as a central authentication point, securing all backend services and validating JWTs. 
Integrated Keycloak IDs into the User Service to sync user data and maintain a single source of truth for identity.

5)API Gateway & Configuration: Set up Spring Cloud Gateway for centralized routing, load balancing, and authentication, abstracting service complexity from the frontend. 
Managed configurations dynamically across all microservices using Spring Cloud Config Server, simplifying deployment and environment management.

6)Frontend Development (React): Built a responsive Single Page Application (SPA) using React for seamless user interaction, including dedicated screens for the Activity Form, Activity List
, and detailed Activity/Recommendation View. Enabled secure frontend authentication by integrating with the Keycloak client library, successfully managing token lifecycle and API calls.

7)End-to-End Performance: Successfully tested and validated end-to-end workflow from frontend submission (Activity Form) $\rightarrow$ Gateway Security $\rightarrow$ Activity Service $\rightarrow$ Kafka $\rightarrow$ AI Service $\rightarrow$ Data Retrieval (Activity Detail Page)
, confirming system stability and data flow integrity.
