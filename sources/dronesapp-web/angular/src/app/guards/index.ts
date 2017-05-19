import { AuthGuard } from './auth.guard/auth.guard';
import { PilotGuard } from './pilot.guard/pilot.guard';
import { ClientGuard } from './client.guard/client.guard';

export const Guards = [
  AuthGuard,
  ClientGuard,
  PilotGuard
];
