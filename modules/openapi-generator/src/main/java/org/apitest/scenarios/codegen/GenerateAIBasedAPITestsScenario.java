package org.apitest.scenarios.codegen;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.core.credential.AzureKeyCredential;
import java.util.ArrayList;
import java.util.List;

public class GenerateAIBasedAPITestsScenario {
    
	private static final String OPENAI_API_KEY = "f62a2e9e9989487384827d169152f737";
    private static final String ENDPOINT = "https://vens-openai.openai.azure.com/";
    private OpenAIClient client; 
    
    public GenerateAIBasedAPITestsScenario() {
    	
    	this.client = new OpenAIClientBuilder()
        	    .credential(new AzureKeyCredential(OPENAI_API_KEY))
        	    .endpoint(ENDPOINT)
        	    .buildClient();
    	
    }
    
    public String Run(String userPrompt) {
    	List<ChatRequestMessage> chatMessages = new ArrayList<>();
    	chatMessages.add(new ChatRequestSystemMessage("I am an AI assistant which helps in providing API testing scenarios for a given swagger operation schema in JSON. I given API testing scenarios in the JSON format."));
    	chatMessages.add(new ChatRequestUserMessage("{\"tags\":[\"user\"],\"summary\":\"Logs user into the system\",\"operationId\":\"loginUser\",\"parameters\":[{\"name\":\"username\",\"in\":\"query\",\"description\":\"The user name for login\",\"required\":true,\"schema\":{\"type\":\"string\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"string\"]},\"extensions\":{}},{\"name\":\"password\",\"in\":\"query\",\"description\":\"The password for login in clear text\",\"required\":true,\"schema\":{\"type\":\"string\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"string\"]},\"extensions\":{}}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"headers\":{\"X-Rate-Limit\":{\"description\":\"calls per hour allowed by the user\",\"schema\":{\"type\":\"integer\",\"format\":\"int32\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"integer\"]}},\"X-Expires-After\":{\"description\":\"date in UTC when token expires\",\"schema\":{\"type\":\"string\",\"format\":\"date-time\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"string\"]}}},\"content\":{\"application/json\":{\"schema\":{\"type\":\"string\",\"exampleSetFlag\":false,\"types\":[\"string\"]},\"exampleSetFlag\":false},\"application/xml\":{\"schema\":{\"type\":\"string\",\"exampleSetFlag\":false,\"types\":[\"string\"]},\"exampleSetFlag\":false}},\"extensions\":{}},\"400\":{\"description\":\"Invalid username/password supplied\",\"content\":{},\"extensions\":{}}},\"extensions\":{}}\r\n"
    			+ ""));
    	chatMessages.add(new ChatRequestAssistantMessage("[\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Valid Login\",\r\n"
    			+ "    \"description\": \"Test the API with valid credentials.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"valid_user\",\r\n"
    			+ "      \"password\": \"valid_password\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"A string representing a token\",\r\n"
    			+ "      \"headers\": {\r\n"
    			+ "        \"X-Rate-Limit\": \"Numeric value indicating the calls per hour allowed.\",\r\n"
    			+ "        \"X-Expires-After\": \"Date in UTC when the token expires.\"\r\n"
    			+ "      }\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Missing Username\",\r\n"
    			+ "    \"description\": \"Test the API when the username is not provided.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"password\": \"valid_password\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid username/password supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Missing Password\",\r\n"
    			+ "    \"description\": \"Test the API when the password is not provided.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"valid_user\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid username/password supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Invalid Credentials\",\r\n"
    			+ "    \"description\": \"Test the API with incorrect username and password.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"invalid_user\",\r\n"
    			+ "      \"password\": \"invalid_password\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid username/password supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Username with Special Characters\",\r\n"
    			+ "    \"description\": \"Test the API with a username containing special characters.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"user@name\",\r\n"
    			+ "      \"password\": \"valid_password\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"A string representing a token\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Password with Special Characters\",\r\n"
    			+ "    \"description\": \"Test the API with a password containing special characters.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"valid_user\",\r\n"
    			+ "      \"password\": \"pass!@#word\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"A string representing a token\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Username and Password with Leading/Trailing Spaces\",\r\n"
    			+ "    \"description\": \"Test the API with a username and password containing leading/trailing spaces.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"  valid_user  \",\r\n"
    			+ "      \"password\": \"  valid_password  \"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"A string representing a token\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Username and Password with XSS Attempt\",\r\n"
    			+ "    \"description\": \"Test the API with a username and password containing XSS attempt.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"username\": \"<script>alert('XSS')</script>\",\r\n"
    			+ "      \"password\": \"<script>alert('XSS')</script>\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid username/password supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  }\r\n"
    			+ "]"));
    	
    	chatMessages.add(new ChatRequestUserMessage("{\"tags\":[\"pet\"],\"summary\":\"Deletes a pet\",\"operationId\":\"deletePet\",\"parameters\":[{\"name\":\"api_key\",\"in\":\"header\",\"schema\":{\"type\":\"string\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"string\"]},\"extensions\":{}},{\"name\":\"petId\",\"in\":\"path\",\"description\":\"Pet id to delete\",\"required\":true,\"schema\":{\"type\":\"integer\",\"format\":\"int64\",\"extensions\":{},\"exampleSetFlag\":false,\"types\":[\"integer\"]},\"extensions\":{}}],\"responses\":{\"400\":{\"description\":\"Invalid ID supplied\",\"content\":{},\"extensions\":{}},\"404\":{\"description\":\"Pet not found\",\"content\":{},\"extensions\":{}}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}],\"extensions\":{}}\r\n"
    			+ ""));
    	
    	chatMessages.add(new ChatRequestAssistantMessage("[\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Valid Pet Deletion\",\r\n"
    			+ "    \"description\": \"Test the API with a valid pet ID and a valid API key.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"api_key\": \"valid_api_key\",\r\n"
    			+ "      \"petId\": 12345\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"Empty\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Missing API Key\",\r\n"
    			+ "    \"description\": \"Test the API when the API key is not provided.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"petId\": 12345\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating the missing API key.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Missing Pet ID\",\r\n"
    			+ "    \"description\": \"Test the API when the pet ID is not provided.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"api_key\": \"valid_api_key\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid ID supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Invalid Pet ID Type\",\r\n"
    			+ "    \"description\": \"Test the API with a non-integer value for pet ID.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"api_key\": \"valid_api_key\",\r\n"
    			+ "      \"petId\": \"abc\"\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 400,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid ID supplied.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Pet Not Found\",\r\n"
    			+ "    \"description\": \"Test the API with a valid API key but an invalid pet ID that does not exist.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"api_key\": \"valid_api_key\",\r\n"
    			+ "      \"petId\": 99999\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 404,\r\n"
    			+ "      \"responseBody\": \"An error message indicating pet not found.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Unauthorized Deletion\",\r\n"
    			+ "    \"description\": \"Test the API with a valid pet ID but an invalid or missing API key.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"petId\": 12345\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 401,\r\n"
    			+ "      \"responseBody\": \"An error message indicating unauthorized access.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Unauthorized Operation\",\r\n"
    			+ "    \"description\": \"Test the API with a valid pet ID and API key, but without the required write permissions.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"api_key\": \"valid_api_key\",\r\n"
    			+ "      \"petId\": 12345\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 403,\r\n"
    			+ "      \"responseBody\": \"An error message indicating insufficient permissions.\"\r\n"
    			+ "    }\r\n"
    			+ "  }\r\n"
    			+ "]"));
    	
    	chatMessages.add(new ChatRequestUserMessage("{\"tags\":[\"pet\"],\"summary\":\"Add a new pet to the store\",\"operationId\":\"addPet\",\"requestBody\":{\"description\":\"Pet object that needs to be added to the store\",\"content\":{\"application/json\":{\"schema\":{\"$ref\":\"#/components/schemas/Pet\",\"exampleSetFlag\":false},\"exampleSetFlag\":false},\"application/xml\":{\"schema\":{\"$ref\":\"#/components/schemas/Pet\",\"exampleSetFlag\":false},\"exampleSetFlag\":false}},\"required\":true,\"extensions\":{}},\"responses\":{\"405\":{\"description\":\"Invalid input\",\"content\":{},\"extensions\":{}}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}],\"extensions\":{\"x-codegen-request-body-name\":\"body\"}}\r\n"
    			+ ""));
    	
    	chatMessages.add(new ChatRequestAssistantMessage("[\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Valid Pet Addition\",\r\n"
    			+ "    \"description\": \"Test the API with a valid pet object.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"body\": {\r\n"
    			+ "        \"id\": 123,\r\n"
    			+ "        \"name\": \"Fluffy\",\r\n"
    			+ "        \"category\": {\r\n"
    			+ "          \"id\": 1,\r\n"
    			+ "          \"name\": \"Cats\"\r\n"
    			+ "        },\r\n"
    			+ "        \"photoUrls\": [\"https://example.com/fluffy.jpg\"],\r\n"
    			+ "        \"tags\": [\r\n"
    			+ "          {\r\n"
    			+ "            \"id\": 1,\r\n"
    			+ "            \"name\": \"cute\"\r\n"
    			+ "          }\r\n"
    			+ "        ],\r\n"
    			+ "        \"status\": \"available\"\r\n"
    			+ "      }\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 200,\r\n"
    			+ "      \"responseBody\": \"Pet added successfully.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Missing Pet Object\",\r\n"
    			+ "    \"description\": \"Test the API without providing the pet object.\",\r\n"
    			+ "    \"inputs\": {},\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 405,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid input.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Invalid Pet Object\",\r\n"
    			+ "    \"description\": \"Test the API with an invalid pet object.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"body\": {\r\n"
    			+ "        \"name\": \"Fluffy\",\r\n"
    			+ "        \"photoUrls\": [\"https://example.com/fluffy.jpg\"]\r\n"
    			+ "        // Missing required fields like 'id', 'category', 'tags', 'status'\r\n"
    			+ "      }\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 405,\r\n"
    			+ "      \"responseBody\": \"An error message indicating invalid input.\"\r\n"
    			+ "    }\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"scenario\": \"Unauthorized Operation\",\r\n"
    			+ "    \"description\": \"Test the API without the required write permissions.\",\r\n"
    			+ "    \"inputs\": {\r\n"
    			+ "      \"body\": {\r\n"
    			+ "        \"id\": 123,\r\n"
    			+ "        \"name\": \"Fluffy\",\r\n"
    			+ "        \"category\": {\r\n"
    			+ "          \"id\": 1,\r\n"
    			+ "          \"name\": \"Cats\"\r\n"
    			+ "        },\r\n"
    			+ "        \"photoUrls\": [\"https://example.com/fluffy.jpg\"],\r\n"
    			+ "        \"tags\": [\r\n"
    			+ "          {\r\n"
    			+ "            \"id\": 1,\r\n"
    			+ "            \"name\": \"cute\"\r\n"
    			+ "          }\r\n"
    			+ "        ],\r\n"
    			+ "        \"status\": \"available\"\r\n"
    			+ "      }\r\n"
    			+ "    },\r\n"
    			+ "    \"expectedOutcome\": {\r\n"
    			+ "      \"statusCode\": 403,\r\n"
    			+ "      \"responseBody\": \"An error message indicating insufficient permissions.\"\r\n"
    			+ "    }\r\n"
    			+ "  }\r\n"
    			+ "]"));
    	
    	    	
    	chatMessages.add(new ChatRequestUserMessage(userPrompt));
    	
    	ChatCompletions chatCompletions = client.getChatCompletions("vens-chatgpt-completion1",
    	    new ChatCompletionsOptions(chatMessages));

    	System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
    	
    	String response = "";
    	for (ChatChoice choice : chatCompletions.getChoices()) {
    	    ChatResponseMessage message = choice.getMessage();
    	    System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
    	    System.out.println("Message:");
    	    response += message.getContent();
    	}
    	return response;
    }
    
	/*
	 * public static void main(String[] args) { GenerateAPITestsSceanrios a = new
	 * GenerateAPITestsSceanrios(); String userPrompt = "{\r\n" +
	 * "  \"tags\": [\r\n" + "    \"pet\"\r\n" + "  ],\r\n" +
	 * "  \"summary\": \"Updates a pet in the store with form data\",\r\n" +
	 * "  \"operationId\": \"updatePetWithForm\",\r\n" + "  \"parameters\": [\r\n" +
	 * "    {\r\n" + "      \"name\": \"petId\",\r\n" +
	 * "      \"in\": \"path\",\r\n" +
	 * "      \"description\": \"ID of pet that needs to be updated\",\r\n" +
	 * "      \"required\": true,\r\n" + "      \"schema\": {\r\n" +
	 * "        \"type\": \"integer\",\r\n" + "        \"format\": \"int64\",\r\n" +
	 * "        \"extensions\": {},\r\n" + "        \"exampleSetFlag\": false,\r\n"
	 * + "        \"types\": [\r\n" + "          \"integer\"\r\n" + "        ]\r\n"
	 * + "      },\r\n" + "      \"extensions\": {}\r\n" + "    }\r\n" + "  ],\r\n"
	 * + "  \"requestBody\": {\r\n" + "    \"content\": {\r\n" +
	 * "      \"application/x-www-form-urlencoded\": {\r\n" +
	 * "        \"schema\": {\r\n" +
	 * "          \"$ref\": \"#/components/schemas/updatePetWithForm_request\",\r\n"
	 * + "          \"exampleSetFlag\": false\r\n" + "        },\r\n" +
	 * "        \"exampleSetFlag\": false\r\n" + "      }\r\n" + "    }\r\n" +
	 * "  },\r\n" + "  \"responses\": {\r\n" + "    \"405\": {\r\n" +
	 * "      \"description\": \"Invalid input\",\r\n" +
	 * "      \"content\": {},\r\n" + "      \"extensions\": {}\r\n" + "    }\r\n" +
	 * "  },\r\n" + "  \"security\": [\r\n" + "    {\r\n" +
	 * "      \"petstore_auth\": [\r\n" + "        \"write:pets\",\r\n" +
	 * "        \"read:pets\"\r\n" + "      ]\r\n" + "    }\r\n" + "  ],\r\n" +
	 * "  \"extensions\": {}\r\n" + "}"; String response = a.Run(userPrompt); }
	 */
    
   
}
