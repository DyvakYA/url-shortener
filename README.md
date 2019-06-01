![Travis](https://travis-ci.org/DyvakYA/url-shortener.png?branch=master) 

## Usage

### Get short url

**Defination**

`POST /urlshortener/v1/url`
```json
{
      "longUrl":"http://www.google.com"
}
```
**Response**

- `200 OK` on succes

```json
{
	"kind": "urlshortener#url",
	"shortUrl": "http://localhost/V5Mh5R",
	"longUrl": "http://www.google.com"
}	
```

### Use short url

**Defination**

`GET /V5Mh5R`

**Response**

- `302 FOUND` on succes

You will be redirected to the place where you want to be. Magic.

### License

**Free Software, Hell Yeah!**



