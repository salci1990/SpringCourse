package services;

import com.clockworkjava.kursspring.domain.Quest;
import com.clockworkjava.kursspring.domain.repository.QuestRepository;
import com.clockworkjava.kursspring.services.QuestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestServiceTest {

    @Mock
    QuestRepository repositoryMock;

    @Test
    public void returnNotStartedQuestP(){

        List<Quest> quests = new ArrayList<>();
        Quest q1 = new Quest("Test quest q1");
        Quest q2 = new Quest("Test quest q2");
        Quest q3 = new Quest("Test quest q3");

        q2.setStarted(true);

        quests.add(q1);
        quests.add(q2);
        quests.add(q3);

        when(repositoryMock.getAll()).thenReturn(quests);

        QuestService qs = new QuestService();
        qs.setQuestRepository(repositoryMock);

        List<Quest> allNotStartedQuest = qs.getAllNotStartedQuests();

        assertEquals("Size of returned quest", 2, allNotStartedQuest.size());
        assertThat(allNotStartedQuest, containsInAnyOrder(q1, q3));
    }
}
