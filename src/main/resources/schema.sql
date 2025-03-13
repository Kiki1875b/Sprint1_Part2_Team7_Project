CREATE TABLE departments (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    description VARCHAR(500) NOT NULL,
    established_date TIMESTAMPTZ NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NULL
);

CREATE TABLE binary_contents (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(100) NOT NULL,
    file_size BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NULL
);


CREATE TABLE employees (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    department_id BIGINT NOT NULL,
    binary_content_id BIGINT NULL,
    employee_number VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    job_title VARCHAR(50) NOT NULL,
    hire_date TIMESTAMPTZ NOT NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('재직중', '휴직중', '퇴사')),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NULL,
    CONSTRAINT fk_employees_department FOREIGN KEY (department_id) REFERENCES departments (id),
    CONSTRAINT fk_employees_binary_content FOREIGN KEY (binary_content_id) REFERENCES binary_contents (id) ON DELETE SET NULL
);


CREATE TABLE employee_history (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    employee_number VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL CHECK (type IN ('직원 추가', '정보 수정', '직원 삭제')),
    details JSONB NULL,
    memo TEXT NULL,
    ip_address INET NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT fk_employee_history_employee FOREIGN KEY (employee_number) REFERENCES employees (employee_number) ON SET NULL
);


CREATE TABLE backup_history (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    operator INET NOT NULL,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('진행중', '완료', '실패', '건너뜀')),
    file_id UUID NOT NULL,
    CONSTRAINT fk_backup_history_file FOREIGN KEY (file_id) REFERENCES binary_contents (id) ON DELETE CASCADE
);
