# Security - OAuth

Up to this point, only the user microservice has security added. We would like
to add security to the post microservice, but how do we share users and roles
without directly connecting to the user microservice's database? It would be
nice if we could make the user microservice responsible for managing users and
roles, and somehow share that information with other microservices.

One way of accomplishing this is to use [OAuth]() for security.

## OAuth in the Real World

If you have ever logged into a site using Facebook or Google, you have probably
already used OAuth. One of OAuth's core objectives is to authenticate users
without having to share your username and password with "untrusted" sites.

For example, you can log in to Spotify using your Facebook or Google
credentials. If its your first time, you may see a prompt asking you to grant
Spotify permission to access your account. Once you have done that, you are
logged into Spotify using Facebook or Google.

During this process, Spotify never directly handles your username or password.
Instead, Facebook or Google verify your credentials, and then send Spotify a
token which it may be used to access details of your account. Spotify uses this
token instead of your credentials directly. This minimizes the risk of your
password being stolen by 3rd party applications.

Retrieving a token is accomplished through a series of requests and redirects
that occur between Spotify and Facebook/Google. This process is called a **grant
type**. There are a number of different grant types a 3rd party can use to
acquire a token.

## Grant Types

There are several [grant types]() you can use for your application. Which one
you choose depends greatly on what you are trying to accomplish. We will use the
[password grant type]() to get started, though [this grant type comes with a
number of drawbacks](drawbacks). However, its simplicity will make it easier for
us to start exploring OAuth, so we will start with that knowing it may not be
the perfect long-term solution.

## Server Types

OAuth has two different server concepts: authorization and resource servers.


### Authorization Server



### Resource Server

[oauth]: https://oauth.net/2/
[grant types]: https://oauth.net/2/grant-types/
[password grant type]:https://developer.okta.com/blog/2018/06/29/what-is-the-oauth2-password-grant
[drawbacks]: https://www.identityserver.com/articles/an-introduction-to-the-oauth-device-flow/
