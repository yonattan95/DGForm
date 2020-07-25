import { Module } from '@nestjs/common';
import { FormModule } from './form_module/form.module';
import { Form } from './form_module/data/entities/form.entity';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Connection } from 'typeorm';

@Module({
  imports: [
    FormModule,
    TypeOrmModule.forRoot({
      // useFactory: () => {
      //   return {
      type: 'postgres',
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
  ],
})
export class AppModule {
  constructor(private connection: Connection) {}
}
