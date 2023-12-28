DROP TABLE IF EXISTS step, material, project_category, category, project;

CREATE TABLE IF NOT EXISTS project (
	project_id int NOT NULL AUTO_INCREMENT, 
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    PRIMARY KEY (project_id)
);
CREATE TABLE IF NOT EXISTS category (
	category_id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (category_id)
);
CREATE TABLE IF NOT EXISTS project_category (
	project_id INT NOT NULL,
    category_id INT NOT NULL,
    UNIQUE KEY (project_id, category_id),
    UNIQUE KEY (category_id, project_id),
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS material (
	material_id INT NOT NULL AUTO_INCREMENT,
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    PRIMARY KEY (material_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS step (
	step_id INT NOT NULL,
    project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    PRIMARY KEY (step_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);