import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {AuthGuard} from './utils/auth.guard';
import {AnnouncementComponent} from './announcement/announcement.component';
import {AnnouncementDetailsComponent} from './announcement/announcement-details/announcement-details.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';


const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  { path: 'announcement', component: AnnouncementComponent},
  { path: 'announcement/:id', component: AnnouncementDetailsComponent},
  { path: 'admin', component: AdminPanelComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
