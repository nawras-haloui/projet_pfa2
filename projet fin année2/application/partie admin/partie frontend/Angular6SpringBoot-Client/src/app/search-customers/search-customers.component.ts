import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'search-customers',
  templateUrl: './search-customers.component.html',
  styleUrls: ['./search-customers.component.css']
})
export class SearchCustomersComponent implements OnInit {

  username: string;
  users: User[];

  constructor(private dataService: UserService) { }

  ngOnInit() {
    this.username = "";
  }

  private searchUsers() {
    this.dataService.getUsersByUsername(this.username)
      .subscribe(users => this.users = users);
  }

  onSubmit() {
    this.searchUsers();
  }
}
