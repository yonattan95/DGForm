import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

enum FormStatusEnum {
  'pendiente' = 0,
  'completado' = 1,
}

@Entity()
export class Form {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  description: string;

  @Column({ default: 1 })
  state: FormStatusEnum;

  @Column()
  userAdminId: number;

  @Column({ default: new Date() })
  createdDate: Date;
}
