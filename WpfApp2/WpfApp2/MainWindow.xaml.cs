using System;
using System.Data;
using System.Data.SQLite;
using System.Windows;
using System.Windows.Controls;

namespace AISDatabaseApp
{
    public partial class MainWindow : Window
    {
        // Строка подключения к базе данных SQLite
        string connectionString = "Data Source=mydatabase.db;Version=3;";

        public MainWindow()
        {
            InitializeComponent();
            LoadData();
        }

        // Загрузка данных в DataGrid
        private void LoadData()
        {
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                LoadDataIntoGrid(connection);
                connection.Close();
            }
        }

        // Загрузка данных в DataGrid
        private void LoadDataIntoGrid(SQLiteConnection connection)
        {
            // Загрузка данных для каждого DataGrid
            LoadTableData(connection, "SELECT * FROM Project", dataGridProjects);
            LoadTableData(connection, "SELECT * FROM Employee", dataGridEmployees);
            LoadTableData(connection, "SELECT * FROM Client", dataGridClients);
            LoadTableData(connection, "SELECT * FROM Orders", dataGridOrders);
        }

        // Загрузка данных из таблицы в DataGrid
        private void LoadTableData(SQLiteConnection connection, string query, DataGrid dataGrid)
        {
            using (var command = new SQLiteCommand(query, connection))
            using (var reader = command.ExecuteReader())
            {
                var dataTable = new DataTable();
                dataTable.Load(reader);
                dataGrid.ItemsSource = dataTable.DefaultView;
            }
        }

        // Выполнение SQL команды без получения результата
        private void ExecuteNonQuery(SQLiteConnection connection, string commandText)
        {
            using (var command = new SQLiteCommand(commandText, connection))
            {
                command.ExecuteNonQuery();
            }
        }

        // Добавление проекта
        private void AddProjectButton_Click(object sender, RoutedEventArgs e)
        {
            // Открываем форму для ввода данных проекта
            ProjectExpander.IsExpanded = true;
        }

// Сохранение нового проекта
        private void SaveProjectButton_Click(object sender, RoutedEventArgs e)
        {
            // Проверка ввода данных
            if (string.IsNullOrWhiteSpace(ProjectNameTextBox.Text) ||
                string.IsNullOrWhiteSpace(ProjectBudgetTextBox.Text) ||
                string.IsNullOrWhiteSpace(ProjectTimelineTextBox.Text) ||
                string.IsNullOrWhiteSpace(ProjectStatusTextBox.Text))
            {
                MessageBox.Show("Please fill in all project fields.");
                return;
            }

            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "INSERT INTO Project (Project_Name, Description, Start_Date, End_Date) VALUES (@name, @description, @startDate, @endDate)";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", ProjectNameTextBox.Text);
                    command.Parameters.AddWithValue("@description", ProjectBudgetTextBox.Text);
                    command.Parameters.AddWithValue("@startDate", ProjectTimelineTextBox.Text);
                    command.Parameters.AddWithValue("@endDate", ProjectStatusTextBox.Text);
                    command.ExecuteNonQuery();
                }

                // Закрыть форму после добавления
                ProjectExpander.IsExpanded = false;

                LoadData(); // Обновляем данные в DataGrid
            }
        }

        // Поиск проекта
        private void SearchProjectButton_Click(object sender, RoutedEventArgs e)
        {
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Project WHERE Project_name LIKE @name";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", "%" + ProjectSearchTextBox.Text + "%");
                    using (var reader = command.ExecuteReader())
                    {
                        var dataTable = new DataTable();
                        dataTable.Load(reader);
                        dataGridProjects.ItemsSource = dataTable.DefaultView;
                    }
                }
            }
        }

        // Удаление проекта
        private void DeleteProjectButton_Click(object sender, RoutedEventArgs e)
        {
            if (dataGridProjects.SelectedItem is DataRowView selectedRow)
            {
                var projectId = selectedRow["project_ID"];
                using (var connection = new SQLiteConnection(connectionString))
                {
                    connection.Open();
                    var query = "DELETE FROM Project WHERE project_ID = @id";
                    using (var command = new SQLiteCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@id", projectId);
                        command.ExecuteNonQuery();
                    }
                    LoadData();
                }
            }
        }

        private void AddClientButton_Click(object sender, RoutedEventArgs e)
        {
            // Открываем форму для ввода данных клиента
            ClientExpander.IsExpanded = true;
        }

// Сохранение нового клиента
        private void SaveClientButton_Click(object sender, RoutedEventArgs e)
        {
            // Проверка ввода данных
            if (string.IsNullOrWhiteSpace(ClientNameTextBox.Text) ||
                string.IsNullOrWhiteSpace(ClientEmailTextBox.Text) ||
                string.IsNullOrWhiteSpace(ClientPhoneTextBox.Text) ||
                string.IsNullOrWhiteSpace(ClientAddressTextBox.Text))
            {
                MessageBox.Show("Please fill in all client fields.");
                return;
            }

            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "INSERT INTO Client (Full_Name, Email, Phone, Address) VALUES (@name, @email, @phone, @address)";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", ClientNameTextBox.Text);
                    command.Parameters.AddWithValue("@email", ClientEmailTextBox.Text);
                    command.Parameters.AddWithValue("@phone", ClientPhoneTextBox.Text);
                    command.Parameters.AddWithValue("@address", ClientAddressTextBox.Text);
                    command.ExecuteNonQuery();
                }

                // Закрыть форму после добавления
                ClientExpander.IsExpanded = false;

                LoadData(); // Обновляем данные в DataGrid
            }
        }

        // Поиск клиента
        private void SearchClientButton_Click(object sender, RoutedEventArgs e)
        {
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Client WHERE company_name LIKE @name";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", "%" + ClientSearchTextBox.Text + "%");
                    using (var reader = command.ExecuteReader())
                    {
                        var dataTable = new DataTable();
                        dataTable.Load(reader);
                        dataGridClients.ItemsSource = dataTable.DefaultView;
                    }
                }
            }
        }

        // Удаление клиента
        private void DeleteClientButton_Click(object sender, RoutedEventArgs e)
        {
            if (dataGridClients.SelectedItem is DataRowView selectedRow)
            {
                var clientId = selectedRow["client_ID"];
                using (var connection = new SQLiteConnection(connectionString))
                {
                    connection.Open();
                    var query = "DELETE FROM Client WHERE client_ID = @id";
                    using (var command = new SQLiteCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@id", clientId);
                        command.ExecuteNonQuery();
                    }
                    LoadData();
                }
            }
        }

        // Добавление сотрудника
          // Добавление нового сотрудника
        private void AddEmployeeButton_Click(object sender, RoutedEventArgs e)
        {
            // Открываем форму для ввода данных сотрудника
            EmployeeExpander.IsExpanded = true;
        }

        // Сохранение нового сотрудника
        private void SaveEmployeeButton_Click(object sender, RoutedEventArgs e)
        {
            // Проверка ввода данных
            if (string.IsNullOrWhiteSpace(EmployeeNameTextBox.Text) ||
                string.IsNullOrWhiteSpace(EmployeePositionTextBox.Text) ||
                string.IsNullOrWhiteSpace(EmployeeContractTextBox.Text) ||
                string.IsNullOrWhiteSpace(EmployeeSalaryTextBox.Text) ||
                string.IsNullOrWhiteSpace(EmployeeExperienceTextBox.Text) ||
                string.IsNullOrWhiteSpace(EmployeeProjectTextBox.Text))
            {
                MessageBox.Show("Please fill in all employee fields.");
                return;
            }

            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "INSERT INTO Employee (Full_Name, Position, Contract_info, Salary, Experience, project_ID) VALUES (@name, @position, @contract, @salary, @experience, @projectId)";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", EmployeeNameTextBox.Text);
                    command.Parameters.AddWithValue("@position", EmployeePositionTextBox.Text);
                    command.Parameters.AddWithValue("@contract", EmployeeContractTextBox.Text);
                    command.Parameters.AddWithValue("@salary", Convert.ToDecimal(EmployeeSalaryTextBox.Text));
                    command.Parameters.AddWithValue("@experience", Convert.ToInt32(EmployeeExperienceTextBox.Text));
                    command.Parameters.AddWithValue("@projectId", Convert.ToInt32(EmployeeProjectTextBox.Text));
                    command.ExecuteNonQuery();
                }

                // Закрыть форму после добавления
                EmployeeExpander.IsExpanded = false;

                LoadData(); // Обновляем данные в DataGrid
            }
        }

        // Поиск сотрудника
        private void SearchEmployeeButton_Click(object sender, RoutedEventArgs e)
        {
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Employee WHERE Full_Name LIKE @name";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@name", "%" + EmployeeSearchTextBox.Text + "%");
                    using (var reader = command.ExecuteReader())
                    {
                        var dataTable = new DataTable();
                        dataTable.Load(reader);
                        dataGridEmployees.ItemsSource = dataTable.DefaultView;
                    }
                }
            }
        }

        // Удаление сотрудника
        private void DeleteEmployeeButton_Click(object sender, RoutedEventArgs e)
        {
            if (dataGridEmployees.SelectedItem is DataRowView selectedRow)
            {
                var employeeId = selectedRow["employee_ID"];
                using (var connection = new SQLiteConnection(connectionString))
                {
                    connection.Open();
                    var query = "DELETE FROM Employee WHERE employee_ID = @id";
                    using (var command = new SQLiteCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@id", employeeId);
                        command.ExecuteNonQuery();
                    }
                    LoadData();
                }
            }
        }

        // Добавление заказа
        private void AddOrderButton_Click(object sender, RoutedEventArgs e)
        {
            // Открываем форму для ввода данных заказа
            OrderExpander.IsExpanded = true;
        }

// Сохранение нового заказа
        private void SaveOrderButton_Click(object sender, RoutedEventArgs e)
        {
            // Проверка ввода данных
            if (string.IsNullOrWhiteSpace(OrderDateTextBox.Text) ||
                string.IsNullOrWhiteSpace(OrderBudgetTextBox.Text) ||
                string.IsNullOrWhiteSpace(OrderTimelineTextBox.Text) ||
                string.IsNullOrWhiteSpace(OrderClientIdTextBox.Text) ||
                string.IsNullOrWhiteSpace(OrderProjectIdTextBox.Text))
            {
                MessageBox.Show("Please fill in all order fields.");
                return;
            }

            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "INSERT INTO Orders (order_date, Budget, Timeline, Client_ID, project_ID) VALUES (@date, @budget, @timeline, @clientId, @projectId)";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@date", OrderDateTextBox.Text);
                    command.Parameters.AddWithValue("@budget", Convert.ToDecimal(OrderBudgetTextBox.Text));
                    command.Parameters.AddWithValue("@timeline", Convert.ToInt32(OrderTimelineTextBox.Text));
                    command.Parameters.AddWithValue("@clientId", Convert.ToInt32(OrderClientIdTextBox.Text));
                    command.Parameters.AddWithValue("@projectId", Convert.ToInt32(OrderProjectIdTextBox.Text));
                    command.ExecuteNonQuery();
                }

                // Закрыть форму после добавления
                OrderExpander.IsExpanded = false;

                LoadData(); // Обновляем данные в DataGrid
            }
        }

        // Поиск заказа
        private void SearchOrderButton_Click(object sender, RoutedEventArgs e)
        {
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Orders WHERE order_ID LIKE @id";
                using (var command = new SQLiteCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@id", "%" + OrderSearchTextBox.Text + "%");
                    using (var reader = command.ExecuteReader())
                    {
                        var dataTable = new DataTable();
                        dataTable.Load(reader);
                        dataGridOrders.ItemsSource = dataTable.DefaultView;
                    }
                }
            }
        }

        // Удаление заказа
        private void DeleteOrderButton_Click(object sender, RoutedEventArgs e)
        {
            if (dataGridOrders.SelectedItem is DataRowView selectedRow)
            {
                var orderId = selectedRow["order_ID"];
                using (var connection = new SQLiteConnection(connectionString))
                {
                    connection.Open();
                    var query = "DELETE FROM Orders WHERE order_ID = @id";
                    using (var command = new SQLiteCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@id", orderId);
                        command.ExecuteNonQuery();
                    }
                    LoadData();
                }
            }
        }
    }
}
