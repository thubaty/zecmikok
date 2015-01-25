package sk.th.zecmikok.service.internal;

import org.springframework.stereotype.Service;
import sk.th.zecmikok.service.HomeService;
import sk.th.zecmikok.service.LearnUnitDto;
import sk.th.zecmikok.service.WordDto;

import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {

    Map<Long, LearnUnitDto> listDb = new HashMap<Long, LearnUnitDto>();
    Map<Long, List<WordDto>> wordDb = new HashMap<Long, List<WordDto>>();


    {
        listDb.put(1l, new LearnUnitDto(1l, "list1", "wordset", 40l));
        listDb.put(2l, new LearnUnitDto(2l, "list2", "wordset", 40l));
        listDb.put(3l, new LearnUnitDto(3l, "pastperfect", "grammar", 40l));
        listDb.put(4l, new LearnUnitDto(4l, "atWork1", "homework", 40l));

        {
            List<WordDto> words = new ArrayList<WordDto>();
            words.add(new WordDto(1l, "slovo1", "word1", 10l, 10l, false));
            words.add(new WordDto(2l, "slovo2", "word2", 10l, 10l, false));
            words.add(new WordDto(3l, "slovo3", "word3", 10l, 10l, false));
            words.add(new WordDto(4l, "slovo4", "word4", 10l, 10l, false));
            wordDb.put(1l, words);
        }

        {
            List<WordDto> words = new ArrayList<WordDto>();
            words.add(new WordDto(5l, "slovo51", "word1", 10l, 10l, false));
            words.add(new WordDto(6l, "slovo62", "word2", 10l, 10l, false));
            words.add(new WordDto(7l, "slovo73", "word3", 10l, 10l, false));
            words.add(new WordDto(8l, "slovo84", "word4", 10l, 10l, false));
            wordDb.put(2l, words);
        }
    }

    @Override
    public List<LearnUnitDto> getAllLearnUnits() {
        Set<Long> ids = listDb.keySet();
        List<LearnUnitDto> list = new ArrayList<LearnUnitDto>();
        for (Long id : ids) {
            list.add(listDb.get(id));
        }
        return list;
    }

    @Override
    public LearnUnitDto getLearnUnit(Long unitId) {
        return listDb.get(unitId);
    }

    @Override
    public List<WordDto> getWordsForLearnUnit(Long unitId) {
        return wordDb.get(unitId);
    }

    @Override
    public WordDto updateWord(Long wordId, WordDto wordDto) {
        Long idToFind = wordId;
        Set<Long> keySet = this.wordDb.keySet();
        for (Long aLong : keySet) {
            List<WordDto> wordDtos = wordDb.get(aLong);
            for (WordDto currentDto : wordDtos) {
                if (currentDto.getId().equals(idToFind)) {
                    currentDto.setDone(wordDto.isDone());
                    currentDto.setState(wordDto.getState());
                    return wordDto;
                }
            }
        }
        return null;
    }
}