<?php
class DB {
    private $dbHost     = "sql213.epizy.com";
    private $dbUsername = "epiz_32587445";
    private $dbPassword = "9MGeQyQ1uQ1h";
    private $dbName     = "epiz_32587445_loginsystem";
  
    public function __construct() {
        if(!isset($this->db)){
            // Connect to the database
            $conn = new mysqli($this->dbHost, $this->dbUsername, $this->dbPassword, $this->dbName);
            if($conn->connect_error){
                die("Failed to connect with MySQL: " . $conn->connect_error);
            }else{
                $this->db = $conn;
            }
        }
    }
  
    # Checks if email and password is in database and returns info (used for login)
    public function check_credentials($email = '', $password = '') {
        $sql = $this->db->query("SELECT id, lastname, firstname, adminpriv FROM employees WHERE email = '$email' AND password = '$password'");
 
        if($sql->num_rows) {
 
            $result = $sql->fetch_assoc();
            return array('status' => 'success',
            'id' => $result['id'],
            'lastname' => $result['lastname'],
            'firstname' => $result['firstname'],
            'adminpriv' => $result['adminpriv']);
        }
         
        return array('status' => 'error', 'message' => 'Email or password is invalid.');
    }

    # Returns array of all records for employee, given last name and last 4 digits of SSN
    public function employee_records($lastname = '', $id = '') {
        $sql = $this->db->query("SELECT * FROM employees WHERE lastname = '$lastname' AND id = '$id'");
 
        # The provided last name and 4 digits of SSN match an entry, returns information
        if($sql->num_rows) {
 
            $result = $sql->fetch_assoc();
            return array('status' => 'success', 
                            'id' => $result['id'], 
                            'lastname' => $result['lastname'],
                            'firstname' => $result['firstname'],
                            'adminpriv' => $result['adminpriv'],
                            'email' => $result['email'],
                            'phone' => $result['phone'],
                            'address' => $result['address'],
                            'city' => $result['city'],
                            'state' => $result['state'],
                            'zip' => $result['zip'],
                            'DOB' => $result['DOB'],
                            'SSN' => $result['SSN'],
                            'currentemployee' => $result['currentemployee'],
                            'startDate' => $result['startDate'],
                            'endDate' => $result['endDate'],
                            'education' => $result['education'],
                            'department' => $result['department'],
                            'position' => $result['position'],
                            'salary' => $result['salary'],
                            'routingnum' => $result['routingnum'],
                            'banktype' => $result['banktype']);
        }
         
        # The provided last name and 4 digits of SSN do not match an entry, returns error
        return array('status' => 'error', 'message' => 'Provided information does not match any records.');
    }

    public function add_record($lastname = '',
                                $firstname = '',
                                $adminpriv = '',
                                $email = '',
                                $password = '',
                                $phone = '',
                                $address = '',
                                $city = '',
                                $state = '',
                                $zip = '',
                                $DOB = '',
                                $SSN = '',
                                $currentemployee = '',
                                $startDate = '',
                                $endDate = '',
                                $education = '',
                                $department = '',
                                $position = '',
                                $salary = '',
                                $routingnum = '',
                                $banktype = '') {
        $sql = $this->db->query("INSERT INTO employees (lastname, firstname, adminpriv, email, password, phone, address, city, state, zip, DOB, SSN, currentemployee, startDate, endDate, education, department, position, salary, routingnum, banktype) VALUES ('$lastname',
                                                                '$firstname',
                                                                '$adminpriv',
                                                                '$email',
                                                                '$password',
                                                                '$phone',
                                                                '$address',
                                                                '$city',
                                                                '$state',
                                                                '$zip',
                                                                '$DOB',
                                                                '$SSN',
                                                                '$currentemployee',
                                                                '$startDate',
                                                                '$endDate',
                                                                '$education',
                                                                '$department',
                                                                '$position',
                                                                '$salary',
                                                                '$routingnum',
                                                                '$banktype')");
    }

    # Returns array of all records for employee, given employee ID and any admin password
    public function adminview_records($id = '', $adminpass = '') {
        $sql = $this->db->query("SELECT adminpriv FROM employees WHERE password = '$adminpass'");

        # Checks if admin password matched any entry in database
        if($sql->num_rows) {
            $result = $sql->fetch_assoc();
            $result = array('adminpriv' => $result['adminpriv']);

            # The password given is an admin password
            if($result['adminpriv']) {
                $sql = $this->db->query("SELECT * FROM employees WHERE id = '$id'");

                # Provided ID matches an employee, returns information from database
                if($sql->num_rows) {
                    $result = $sql->fetch_assoc();
                    return array('status' => 'success', 
                                    'id' => $result['id'], 
                                    'lastname' => $result['lastname'],
                                    'firstname' => $result['firstname'],
                                    'adminpriv' => $result['adminpriv'],
                                    'email' => $result['email'],
                                    'phone' => $result['phone'],
                                    'address' => $result['address'],
                                    'city' => $result['city'],
                                    'state' => $result['state'],
                                    'zip' => $result['zip'],
                                    'DOB' => $result['DOB'],
                                    'SSN' => $result['SSN'],
                                    'currentemployee' => $result['currentemployee'],
                                    'startDate' => $result['startDate'],
                                    'endDate' => $result['endDate'],
                                    'education' => $result['education'],
                                    'department' => $result['department'],
                                    'position' => $result['position'],
                                    'salary' => $result['salary'],
                                    'routingnum' => $result['routingnum'],
                                    'banktype' => $result['banktype']);
                }
                # Provided ID does not match any employee, returns error
                return array('status' => 'error', 'message' => 'Provided ID does not match any records.'); 
            }
            # The password given is a user password, returns error
            return array('status' => 'error', 'message' => 'Provided password is not an administrator password.');
        }
        # The password does not match any entry in database, returns error
        return array('status' => 'error', 'message' => 'Provided password does not match any records.');
    }

    public function save_record($id = '',
                                $lastname = '',
                                $firstname = '',
                                $email = '',
                                $phone = '',
                                $address = '',
                                $city = '',
                                $state = '',
                                $zip = '',
                                $DOB = '',
                                $currentemployee = '',
                                $startDate = '',
                                $endDate = '',
                                $education = '',
                                $department = '',
                                $position = '',
                                $salary = '',
                                $routingnum = '',
                                $banktype = '') {
                                    
        $sql = $this->db->query("UPDATE employees SET lastname = '$lastname', firstname = '$firstname', email = '$email', phone = '$phone', address = '$address', city = '$city', state = '$state', zip = '$zip', DOB = '$DOB', currentemployee = '$currentemployee', startDate = '$startDate', endDate = '$endDate', education = '$education', department = '$department', position = '$position', salary = '$salary', routingnum = '$routingnum', banktype = '$banktype' WHERE id = '$id'");
    }

    # Checks admin's password, then deletes the record by ID
    public function delete_record($id = '', $adminpass = '') {
        $sql = $this->db->query("SELECT adminpriv FROM employees WHERE password = '$adminpass'");

        # Checks if admin password matched any entry in database
        if($sql->num_rows) {
            $result = $sql->fetch_assoc();
            $result = array('adminpriv' => $result['adminpriv']);

            # The password given is an admin password
            if($result['adminpriv']) {
                $sql = $this->db->query("DELETE FROM employees WHERE id = '$id'");
                return array('status' => 'success');
            }
            # The password given is a user password, returns error
            return array('status' => 'error', 'message' => 'Provided password is not an administrator password.');
        }
        # The password does not match any entry in database, returns error
        return array('status' => 'error', 'message' => 'Provided password does not match any records.');
    }

    # Adds a grievance/resignation
    public function add_contactForm($formtype = '', $employeeID = '', $comment = '') {
		$sql = $this->db->query("INSERT INTO grievances (type, employeeID, message) VALUES ('$formtype', '$employeeID', '$comment')");
	}

    # Allows admins to view grievances and resignations from the database. Can view all, or a filtered view.
    public function view_grievance($display = '') {
        # display = 0 means view all. grab all results from first query, push them to array, then same thing with second query -> return array
        if($display == '0') 
        {
        
        $sql = $this->db->query("SELECT g.type, e.id, e.lastname, e.firstname, g.message from grievances g, employees e where e.id = g.employeeID");
        $allForms = array();

        while ($result = $sql->fetch_assoc()) 
        {

            array_push($allForms, array('type' => $result['type'], 'id' => $result['id'], 'lastname' => $result['lastname'], 'firstname' => $result['firstname'],  'message' => $result['message']));
        }

        $sql = $this->db->query("SELECT * from grievances where type = 0");

         while ($result = $sql->fetch_assoc()) 
        {

            array_push($allForms, array('type' => $result['type'], 'id' => 'N/A', 'lastname' => 'Anonymous', 'firstname' => 'Employee',  'message' => $result['message']));
        }

        return $allForms;
        }

        return $display;
    }

    public function viewall() {
        $allrecords = array();
        $sql = $this->db->query("SELECT id, lastname, firstname, department, position FROM employees");
        if ($sql->num_rows) {
            while($result = $sql->fetch_assoc()){
                array_push($allrecords, array('ID' => $result['id'], 
                                            'First Name' => $result['firstname'],
                                            'Last Name' => $result['lastname'],
                                            'Department' => $result['department'],
                                            'Position' => $result['position']));
            }
            return array('status' => 'success', 'allrecords' => $allrecords);
        }
        return array('status' => 'error', 'message' => 'No records found.');
    }
}