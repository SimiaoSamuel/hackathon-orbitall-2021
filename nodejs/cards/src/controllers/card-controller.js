const { request, response } = require('express')
const neDB = require('../configurations/database')
const api = {}
//GET     /cards
api.findAll = (request, response) => {
    neDB.find({}, (exception, card) =>{
        if(exception){
            response.status(exception.status | 400);
            response.json('Erro ao listar todos os cards');
        }
        response.status(200);
        response.json(card);
    })
}

//GET     /cards/paginationAndSorting/:numberOfCards
api.paginationAndSort = (request, response) => {
    neDB.find({}).sort({customerName: 1}).limit(request.params.numberOfCards).exec((exception,cards) => {
        if (exception) {
            const msg = 'Erro ao tentar encontrar um card pelo id'
            console.log(msg, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': msg })
            }
            console.log("entrou")
            response.status(200)
            response.json(cards)
    })
}

//POST    /cards
api.save = (request, response) => {
    const newCard = request.body;

    neDB.insert(newCard, (exception, card) => {
        if(exception){
            response.status(exception.status | 400);
            response.json('Erro ao inserir um novo card');
        }
        response.status(201);
        response.json(card);
    });
}

//GET     /cards/{id}
api.findById = (request, response) => {
    neDB.findOne({ _id: request.params.id }, (exception, card) => {
        if (exception) {
        const msg = 'Erro ao tentar encontrar um card pelo id'
        console.log(msg, exception)
        response.status(exception.status | 400)
        response.json({ 'mensagem': msg })
        }
        response.status(200);
        response.json(card);
    })
}

//DELETE  /cards/{id}
api.delete = (request, response) => {
    neDB.remove({_id: request.params.id}, (exception) => {
        if(exception){
            console.log('Erro ao tentar remover esse card',exception)
        }
        response.status(204)
        response.json('')
    })
}

//PUT     /cards/{id}
api.update = (request, response) => {
    neDB.update({_id: request.params.id}, {$set: request.body}, (exception) => {
        if(exception){
            console.log('Erro ao tentar atualizar um card',exception)
        }
        response.status(204);
        response.json('')
    })
}

module.exports = api