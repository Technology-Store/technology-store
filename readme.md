# Technology Store

## Dự án Multi-Store E-commerce withstand the pressure of 10.000.000 concurrent users 

### Mục tiêu 1: Phát triển hệ thống thương mại điện tử đa gian hàng, có khả năng xử lý lượng đồng thời cao (high of concurrency).
Áp dụng mô hình optimistic locking. Khi nhiều người dùng cùng lúc đặt mua sản phẩm, hệ thống sử dụng Redis để lưu trữ và truy xuất nhanh dữ liệu tồn kho. Mỗi khi có yêu cầu đặt hàng, hệ thống sẽ kiểm tra phiên bản dữ liệu trong Redis trước khi cập nhật điều này là để bảo vệ tính toàn vẹn tài nguyên,...coming soon

### Mục tiêu 2: Phát triển Notification service based on microservices architecture.
Với Kafka + Rabbit message queue được sử dụng để đảm bảo rằng các thông báo được xử lý asynchronous, tăng cường khả năng mở rộng và chịu tải cao, đồng thời giảm thiểu độ trễ,...coming soon

### Mục tiêu 3: Triển khai hệ thống bảo mật hiện đại và chặt chẽ nhất
Phát triển hệ thống bảo mật nâng cao sử dụng token pair access/refresh + key pair bất đối xứng (RSA Algorithm) cho mỗi người dùng trong hệ thống.</br>
Tự động phát hiện bất thường và disconnect the anonymous person trong việc xử lý refresh token không hợp lệ.</br>
Triển khai AWS-S3 security server sevice và ES2 comprehensive.

### Mục tiêu 4: Phát triển kiến trúc Elasticsearch tối ưu hóa trãi nghiệm search filter.

### Mục tiêu 5: Phát triển Nested comment, hệ thống realtime-communication.
