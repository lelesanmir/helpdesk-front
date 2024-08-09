import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  logar(): void {
    if (this.loginForm.valid) {
      const creds = this.loginForm.value;
      this.authService.authenticate(creds).subscribe(
        response => {
          if (response && response.token) {
            this.authService.sucessfullLogin(response.token);
            this.router.navigate(['/home']); // Redirecionar para a página inicial ou outra página
          } else {
            console.error('Token não encontrado na resposta:', response);
          }
        },
        error => {
          console.error('Erro ao fazer login:', error);
        }
      );
    }
  }

  validateFields(): boolean {
    return this.loginForm.valid;
  }
}
