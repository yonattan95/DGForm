import { Module } from '@nestjs/common';
import { FormModule } from './form_module/form.module';
import { Form } from './form_module/data/entities/form.entity';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Connection } from 'typeorm';
import { InterviewerModule } from './auth_module/auth.module';
import { UserModule } from './user_module/user.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      // useFactory: () => {
      //   return {
      type: 'postgres',
      // host: 'db_postgres_12',
      host: 'localhost',
      database: 'db_dgform',
      username: 'admin',
      port: 5432,
      password: '123',
      synchronize: true,
      autoLoadEntities: true,
      entities: [Form],
      //   };
      // },
    }),
    FormModule,
    InterviewerModule,
    UserModule,
  ],
})
export class AppModule {
  constructor(private connection: Connection) {}
}
