package Easyui_controller;


import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.Locale;

@Controller
public class DatagridController {

    @RequestMapping("category.json")
    @ResponseBody
    public List<Category> categoryList（@RequestParam(defaultValue = "asc") String order){
        List <Category> categories = new ArrayList<>();
        if("asc".equals(order)){
            categories.add(new Category(id:1, name:"小学"));
            categories.add(new Category(id:2, name:"初中"));
        }else{
            categories.add(new Category(id:2, name:"初中"));
            categories.add(new Category(id:1, name:"小学"));
        }
        return categories;
    }

    @Autowired
    private BookService bookService;

    @RequestMapping("book_list.json")
    @ResponseBody
    public  Map<String, Object> bookList(Book book, Integer page, Integer rows){
        PageInfo<Book> pageInfo = bookService.getBookList(book, page, rows);
        Map<String, Object> result = new HashMap<>();
        result.put("total", pageInfo getTotal());//一共有多少条
        result.put("rows", pageInfo.getList());
        return result;
    }

    @RequestMapping("book_detail.json")
    public ModelAndView getDetail(Integer id){
        Book book = bookService.getBookById(id);
        return new ModelAndView(  "/pages/datagrid/detail.jsp");
    }

}
