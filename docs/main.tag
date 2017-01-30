<card-list>
    <div style="padding: 8px; text-align: left;">
        <div each={opt in opts}>
            <p>
                <card opt={opt}></card>
            </p>
        </div>
    </div>
    <script>
        this.on('mount', function() {
            console.log(opts)
        })
    </script>
</card-list>

<card>
    <div class="demo-card-square mdl-card mdl-shadow--2dp" style="">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">{title}</h2>
        </div>
        <div class="mdl-card__supporting-text">
            {description}<br> 配信：{period}
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href={href}>登録する</a>
        </div>
    </div>
    <!-- Square card -->
    <style>
        .demo-card-square.mdl-card {
            width: 320px;
            height: 160px;
        }
        
        .demo-card-square>.mdl-card__title {
            color: #fff;
            background: bottom right 15% no-repeat #46B6AC;
        }
    </style>


    <script>
        var self = this

        this.on('mount', function() {
            self.title = opts.opt.title
            self.description = opts.opt.description
            self.period = opts.opt.period
            self.href = opts.opt.registUrl
            self.update()
        })
    </script>
</card>