package com.example.noterestapi.service.impl;

import com.example.noterestapi.dto.NoteDto;
import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.dto.UserDto;
import com.example.noterestapi.model.User;
import com.example.noterestapi.repository.IUserRepository;
import com.example.noterestapi.service.IUserService;

import static com.example.noterestapi.util.util.hashSha256;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private NoteService noteService;

    /**
     * This method checks login credentials
     *
     * @param user The user data to login.
     * @return ResponseDto The result of the login operation.
     */
    @Override
    public ResponseDto login(User user) {
        if (user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return new ResponseDto(true, "Please enter your username and password.");
        }
        User userRecord = getUserByUsername(user.getUsername());
        if (userRecord == null || !hashSha256(user.getPassword()).equals(userRecord.getPassword())) {
            return new ResponseDto(true, "Username or password is incorrect!");
        } else {
            return new UserDto(false, "Login successful.", userRecord.getId(), userRecord.getUsername());
        }
    }

    /**
     * This method creates a user in the database.
     *
     * @param user User instance to save.
     * @return User|null Created user object, if.
     */
    @Override
    public ResponseDto addUser(User user) {
        if (user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return new ResponseDto(true, "Username and password must be filled.");
        }
        if (user.getUsername().contains(" ")) {
            return new ResponseDto(true, "Username can not contain whitespaces.");
        }
        if (getUserByUsername(user.getUsername()) != null) {
            return new ResponseDto(true, "Given username already in use.");
        }
        user.setPassword(hashSha256(user.getPassword()));
        userRepository.save(user);
        return new ResponseDto(false);
    }

    /**
     * This method returns all users in the database.
     *
     * @return List<User> All user records.
     */
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * This method returns user that has the given id.
     *
     * @param userId The id of the desired user.
     * @return User|null User with given id if exists, otherwise null.
     */
    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }


    /**
     * This method is to get User by username.
     *
     * @param username The username of the desired user.
     * @return User|null User with given username if exists, otherwise null.
     */
    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * This method is to insert test data to make application run properly.
     * It inserts 5 different users test1, test2, test3, test4 and test5.
     * The passwords of the users are same as their username.
     * It also inserts notes for those users.
     * Open a browser and navigate to 'http://localhost:8080/insertTestData' to add test data while this spring app is running.
     */
    @Override
    public void insertTestData() {
        String loremIpsumText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua. Enim diam vulputate ut pharetra sit amet aliquam id. Congue eu consequat " +
                "ac felis donec et odio. Venenatis lectus magna fringilla urna porttitor rhoncus dolor purus. Leo vel orci " +
                "porta non pulvinar. Pulvinar proin gravida hendrerit lectus a. Amet consectetur adipiscing elit ut aliquam " +
                "purus sit. Nunc non blandit massa enim nec. Vitae proin sagittis nisl rhoncus mattis rhoncus. Sed vulputate " +
                "odio ut enim. Consectetur purus ut faucibus pulvinar elementum. Nisi lacus sed viverra tellus in hac habitasse " +
                "platea dictumst. Duis ut diam quam nulla porttitor massa. At in tellus integer feugiat. Nunc sed augue lacus viverra" +
                " vitae congue eu consequat ac. Id nibh tortor id aliquet lectus proin nibh nisl condimentum. Eget lorem dolor sed " +
                "viverra ipsum nunc. Egestas erat imperdiet sed euismod nisi porta lorem mollis. Bibendum at varius vel pharetra " +
                "vel turpis. Eget dolor morbi non arcu risus quis varius. Morbi blandit cursus risus at ultrices. Viverra tellus in " +
                "hac habitasse platea dictumst. Dui id ornare arcu odio. Scelerisque in dictum non consectetur a erat nam. Nibh ipsum" +
                " consequat nisl vel. Quam vulputate dignissim suspendisse in est ante in. Nulla facilisi nullam vehicula ipsum a arcu " +
                "cursus vitae. Lectus mauris ultrices eros in. Bibendum neque egestas congue quisque egestas diam in arcu. Ac ut consequat" +
                " semper viverra nam libero justo laoreet. Facilisi morbi tempus iaculis urna id. Habitasse platea dictumst vestibulum" +
                " rhoncus. Eu facilisis sed odio morbi quis commodo odio. Nisi lacus sed viverra tellus in hac habitasse platea dictumst." +
                " Etiam erat velit scelerisque in dictum non. Blandit cursus risus at ultrices. Suspendisse potenti nullam ac tortor " +
                "vitae. Diam in arcu cursus euismod. Lorem ipsum dolor sit amet. Imperdiet dui accumsan sit amet nulla facilisi " +
                "morbi. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Et malesuada fames ac turpis. " +
                "Magnis dis parturient montes nascetur ridiculus mus mauris vitae ultricies. Odio pellentesque diam volutpat commodo" +
                " sed egestas egestas. Ultrices vitae auctor eu augue ut lectus arcu bibendum. Arcu vitae elementum curabitur vitae " +
                "nunc sed velit. Mi bibendum neque egestas congue quisque.";

        Random random = new Random();

        for (int i = 1; i < 6; i++) {
            String usernameAndPassword = "test" + i;
            User testUser = new User(usernameAndPassword, usernameAndPassword);
            ResponseDto result = addUser(testUser);
            if (!result.getError()) {
                int noteCount = 30 - i * 5;
                for (int j = 0; j < noteCount; j++) {

                    int titleLength = random.nextInt(40);
                    int titleIndex = random.nextInt(loremIpsumText.length() - titleLength);
                    String title = loremIpsumText.substring(titleIndex, titleIndex + titleLength);

                    int contentLength = random.nextInt(loremIpsumText.length() - 10);
                    int contentIndex = random.nextInt(loremIpsumText.length() - contentLength);
                    String content = loremIpsumText.substring(contentIndex, contentIndex + contentLength);

                    NoteDto noteDto = new NoteDto();
                    noteDto.setUserId(testUser.getId());
                    noteDto.setTitle(title);
                    noteDto.setContent(content);
                    noteService.addNote(noteDto);
                }
            }
        }
    }
}
