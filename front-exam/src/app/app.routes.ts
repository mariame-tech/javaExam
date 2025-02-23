import { Routes } from '@angular/router';
import { ClassesComponent } from './pages/classes/classes.component';
import { StudentComponent } from './pages/students/student.component';
import { ListStudentsComponent } from './pages/list-students/list-students.component';
import { ListClassesComponent } from './pages/list-classes/list-classes.component';
import { RegistrationComponent } from './pages/registration/registration.component';

export const routes: Routes = [

  { path: 'student', component: StudentComponent },
  {path:'classes',component:ClassesComponent},
  {path:'list-student',component:ListStudentsComponent},
  {path:'list-classe',component:ListClassesComponent},
  { path: 'registration', component: RegistrationComponent },
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.routes').then(m => m.WELCOME_ROUTES) }
];
