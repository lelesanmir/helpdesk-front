import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credentials } from 'src/app/models/credentials';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  creds: Credentials = {
    email: '',
    password: ''
  }

  email = new FormControl(null, Validators.email);
  password = new FormControl(null, Validators.minLength(3));

  constructor(
    private toastr: ToastrService,
    private service: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
  }

  logar() {
    this.service.authenticate(this.creds).subscribe(resposta => {
      const authHeader = resposta.headers.get('Authorization');
      if (authHeader) {
        this.service.sucessfullLogin(authHeader.substring(7));
        this.router.navigate(['']);
      } else {
        this.toastr.error('Token not found in response!');
      }
    }, () => {
      this.toastr.error('Invalid user and/or password!');
    });
  }

  validateFields(): boolean {
    return this.email.valid && this.password.valid;
  }
}
