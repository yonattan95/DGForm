import {
  Column,
  Entity,
  PrimaryGeneratedColumn,
  Table,
} from 'typeorm';

@Entity()
export class User {
  constructor() {}
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  user_type: number;

  @Column()
  username: string;

  @Column()
  password: string;

  @Column()
  email: string;
}
