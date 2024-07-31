import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
<<<<<<< HEAD
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
=======
>>>>>>> 66374c9 (Updating and separating project files)

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

<<<<<<< HEAD
  constructor(
    private router: Router,
    private authService: AuthService,
    private toastr: ToastrService) { }
=======
  constructor(private router: Router) { }
>>>>>>> 66374c9 (Updating and separating project files)

  ngOnInit(): void {
    this.router.navigate(['technical'])
  }

<<<<<<< HEAD
  logout() {
    this.router.navigate(['login']);
    this.authService.logout();
    this.toastr.info('Logout successful!', 'Logout', { timeOut: 7000 })
  }

=======
>>>>>>> 66374c9 (Updating and separating project files)
}
