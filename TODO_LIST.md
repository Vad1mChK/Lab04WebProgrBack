# TODO
## Backend
### Database
- [x] Make it so the tables are created properly.
- [ ] Possibly review the way `ZonedDateTime` is handled.
### Service
- [ ] Implement `UserService` and `ShotService`.
- [ ] Implement `ShotRequestDto` and `ShotResponseDto`.
- [ ] Think of the way the current user is checked when sending a user request or shot.
- [ ] Think of proper places to throw `401 Unauthorized`.
- [ ] Load pepper from the environment variable `w_PEPPER`.
- [ ] Logic for shot creation and area hit check.
### Resource
- `UserResource`:
  - [ ] Method `getAllUsers` (`GET https://localhost:19200/lab04-1.0-SNAPSHOT/api/user/all`) should be reserved for debug purposes.
  - [ ] Methods for register, login, and logout.
- `ShotResource`:
  - [ ] Method `getAllShots` (`GET https://localhost:19200/lab04-1.0-SNAPSHOT/api/shot`)
  - [ ] Method `getShot` (`GET https://localhost:19200/lab04-1.0-SNAPSHOT/api/shot/1`)
  - [ ] Method `addShot` (`POST https://localhost:19200/lab04-1.0-SNAPSHOT/api/shot`)
  - [ ] Method `deleteAllShots` (`DELETE https://localhost:19200/lab04-1.0-SNAPSHOT/api/shot` or `GET https://localhost:19200/lab04-1.0-SNAPSHOT/api/shot/delete` in case the host bans `DELETE` requests)
## Frontend
### Pages
- [ ] Dynamic page generation with React
- [ ] Identification/authentication/authorization
- [ ] Store form inputs in `localStorage` (Redux?)
- [ ] Use Belle `Spinner` component for X, Y, and R form inputs
- [ ] Use SVG + canvas to send shots on canvas click
- [ ] Shots can be sent both from the form or from the canvas (if R is set)
- [ ] Error messages display
### Error Pages (4XX, 5XX)
- [ ] Error page generation with React.
- [ ] Error text is loaded from JSON (sample structure: 
    ```json
    {
        "401": {
            "statusCode": "401 Unauthorized",
            "characterName": "Жак Портсман",
            "displayName": "Портсман",
            "message": "Хочешь пройти без авторизации? Чувак, даже на баскетбольное поле посторонних не пускают! Сначала зарегистрируйся или войди.",
            "spriteUrl": "portsman_jacket.webp"
        }
    }
    ```
    )
- [ ] Error pages are rendered with Ace Attorney characters.

### Styles
- SCSS styles for most of the pages:
  - [ ] Three themes: `dark-theme`, `synthwave-theme`, and `light-theme`
  - [ ] Three layouts for platforms: mobile (< 667px), tablet (667px <= ... < 1195px), desktop (>= 1195px)
- Vanilla CSS for error pages.

### Request/response
- [ ] JWT creation.