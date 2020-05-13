package xyz.jabwin.myVertX.controller;

import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import xyz.jabwin.myVertX.pojo.service.MapNavResults;
import xyz.jabwin.myVertX.pojo.service.ServerCarTypeAndPriceDto;
import xyz.jabwin.myVertX.service.InvokeService;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@Controller
public class InvokeController
{
    @Autowired
    private InvokeService invokeService;

    public Single<MapNavResults> getServerCarTypeAndPrice(ServerCarTypeAndPriceDto dto)
    {
        return invokeService.getServerCarTypeAndPrice(dto);
    }
}
