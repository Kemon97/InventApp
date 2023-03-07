@Restcontroller
@RequestMapping("api/v1/rest")

public class historycontroller {
    
    @Autowired
    private historycontroller historyController;

    @GetMapping("/history")
    public Flux<history> get() {

        return historyController.get();
    }

    @PostMapping("/history")
    public Mono<history> create(@Valid @RequestBody) {
        return historyController.save(history)
    }

    @PutMapping("/history")
    public Mono<history> put(){
        return historyController.put();
    }

}