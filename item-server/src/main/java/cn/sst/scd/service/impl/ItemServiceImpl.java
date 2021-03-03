package cn.sst.scd.service.impl;

import cn.sst.scd.entity.Item;
import cn.sst.scd.mapper.ItemRepository;
import cn.sst.scd.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shengtengsun
 * @Description
 * @Date 2021/2/3 下午4:02
 * @Version 1.1.0
 **/
@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private IItemService iItemService;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Item insertItem(String name) {
        Item item = new Item();
        item.setName(name);
        item.setId(1L);
        itemRepository.save(item);

        iItemService.updateItem(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void updateItem(Item item) {
        item.setName(item.getName() +  "修改一下名字");
        itemRepository.save(item);
    }
}
