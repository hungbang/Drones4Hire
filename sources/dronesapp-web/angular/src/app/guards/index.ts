import {AuthGuard} from './auth.guard/auth.guard';
import {PilotGuard} from './pilot.guard/pilot.guard';
import {PilotLicensedGuard} from './pilot-licensed.guard/pilot-licensed.guard';
import {ClientGuard} from './client.guard/client.guard';
import {GuestGuard} from './guest.guard/guest.guard';

export const AppGuards = [
  AuthGuard,
  ClientGuard,
  GuestGuard,
  PilotGuard,
  PilotLicensedGuard,
];
