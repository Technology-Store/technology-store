# Technology Store
## Dự án "Tech store ecommerce multi shop owner"

### Mục tiêu 1: Phát triển hệ thống thương mại điện tử đa gian hàng, có khả năng xử lý lượng đồng thời cao (high of concurrency) 
Redis được sử dụng để xử lý lượng đặt hàng cao bằng cách áp dụng mô hình optimistic locking. Khi nhiều người dùng cùng lúc đặt mua sản phẩm, hệ thống sử dụng Redis để lưu trữ và truy xuất nhanh dữ liệu tồn kho. Mỗi khi có yêu cầu đặt hàng, hệ thống sẽ kiểm tra phiên bản dữ liệu trong Redis trước khi cập nhật điều này là để bảo vệ tính toàn vẹn tài nguyên,...coming soon

### Mục tiêu 2: Phát triển Notification service based on microservices architecture
Với Kafka + Rabbit message queue được sử dụng để đảm bảo rằng các thông báo được xử lý một cách không đồng bộ, tăng cường khả năng mở rộng và chịu tải cao, đồng thời giảm thiểu độ trễ,...coming soon

### Mục tiêu 3: Triển khai hệ thống bảo mật hiện đại và chặt chẽ nhất
Phát triển hệ thống bảo mật nâng cao sử dụng token pair access/refresh + key pair bất đối xứng (RSA Algorithm) cho mỗi người dùng trong hệ thống 
Triển khai AWS-S3 security server sevice và toàn diện về ES2 

### Mục tiêu 4: Phát triển kiến trúc elesticsearch tối ưu hóa trãi nghiệm search filter.

### Mục tiêu 5: Phát triển Nested comment, hệ thống realtime-communication.
