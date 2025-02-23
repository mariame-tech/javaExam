import { Routes } from '@angular/router';
import { ClassesComponent } from './pages/classes/classes.component';
import { StudentComponent } from './pages/students/student.component';
import { RegistrationsComponent } from './pages/registrations/registrations.component';
import { ListStudentsComponent } from './pages/list-students/list-students.component';
import { ListClassesComponent } from './pages/list-classes/list-classes.component';

export const routes: Routes = [

  { path: 'student', component: StudentComponent },
  {path:'classes',component:ClassesComponent},
  {path:'list-student',component:ListStudentsComponent},
  {path:'list-classe',component:ListClassesComponent},
  { path: 'registrations', component: RegistrationsComponent },
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.routes').then(m => m.WELCOME_ROUTES) }
];
