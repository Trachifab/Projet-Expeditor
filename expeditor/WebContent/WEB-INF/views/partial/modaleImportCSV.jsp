<div class="ui modal importCsv">
    <div class="header">Importer un fichier CSV</div>
    <div class="content">
        <form id="formImportCsv" action="ImportCSVServlet" enctype="multipart/form-data" method="POST">
            <p>Indiquez ci-dessous le chemin vers le fichier CSV ou XLS, afin d'ajouter les nouvelles commandes.</p>
            <div>
                <div class="field">
                    <div class="ui action input">
                        <input type="text" id="_attachmentName" required>
                        <label for="attachmentName" class="ui icon button btn-file">
                            <i class="attachment basic icon search"></i>
                            <input type="file" id="attachmentName" name="attachmentName" required style="display: none">
                        </label>
                    </div>
                </div>
            </div>
            <div class="button-container">
                <button class="ui primary submit button" type="submit" name="action" value="import">Importer</button>
            </div>
        </form>
    </div>
</div>