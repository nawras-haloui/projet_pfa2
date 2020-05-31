import { Component, OnInit, Input } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../Student';
import { Router } from '@angular/router';
@Component({
  selector: 'app-contact-component',
  templateUrl: './contact-component.component.html',
  styleUrls: ['./contact-component.component.css']
})
export class ContactComponentComponent implements OnInit {
 
  student: Student = new Student();
  submitted = false;
  invalidLogin =false
  @Input() error: string | null;
  constructor(private studentService: StudentService,
    private router: Router) { }

  ngOnInit(): void {
  }
  newStudent(): void {
    this.submitted = false;
    this.student = new Student();
  }

  save() {
    this.studentService.createStudent(this.student)
      .subscribe(data => {
  
      this.submitted = true;
    
      this.invalidLogin = false
      this.save();  
     }, 
      error => {
        this.invalidLogin = true
        this.error = error.message;} )
       
      }
  

  onSubmit() {
   
    
    this.save();   }
   
  

    
  
 
   
  

}
