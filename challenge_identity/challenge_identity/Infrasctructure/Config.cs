// Copyright (c) Brock Allen & Dominick Baier. All rights reserved.
// Licensed under the Apache License, Version 2.0. See LICENSE in the project root for license information.


using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;

namespace challenge_identity.Infrastructure
{
    public static class Config
    {
        public static IEnumerable<IdentityResource> IdentityResources =>
            new List<IdentityResource>
            {
                new IdentityResources.OpenId(),
                new IdentityResources.Profile(),
            };


        public static IEnumerable<ApiScope> ApiScopes =>
            new List<ApiScope>
            {
                new ApiScope("challengeapi", "Challenge API")
            };

        public static IEnumerable<Client> Clients =>
            new List<Client>
            {
                // Robot framework
                new Client
                {
                    ClientId = "robot-framework",
                    ClientName = "Robot integration tests",
                    ClientSecrets = { new Secret("secret".Sha256()) },
                    AllowAccessTokensViaBrowser = true,
                    AllowedGrantTypes = GrantTypes.ClientCredentials,

                    AllowedScopes = new List<string>
                    {
                        "challengeapi"
                    }
                },

                // React client
                new Client
                {
                    ClientId = "challenge_web",
                    ClientName = "Challenge Web",
                    ClientUri = "http://localhost:3000",

                    AllowedGrantTypes = GrantTypes.Implicit,

                    RequireClientSecret = false,

                    RedirectUris =
                    {
                        "http://localhost:3000/signin-oidc",
                    },

                    PostLogoutRedirectUris = { "http://localhost:3000/signout-oidc" },
                    AllowedCorsOrigins = { "http://localhost:3000" },

                    AllowedScopes = new List<string>
                    {
                        IdentityServerConstants.StandardScopes.OpenId,
                        IdentityServerConstants.StandardScopes.Profile,
                        "challengeapi",
                    },

                    AllowAccessTokensViaBrowser = true
                }
            };
    }
}