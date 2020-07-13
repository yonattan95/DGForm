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

  @Column({ default: 0 })
  state: FormStatusEnum;

  @Column({ name: 'user_id' })
  userId: number;

  @Column({ default: new Date(), name: 'created_date' })
  createdDate: Date;
}
