import { Role } from "./Role";

export interface User {
  userId?: number,
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  enabled: boolean;
  token?: string
  authorities?: Role[],
  role: string
}